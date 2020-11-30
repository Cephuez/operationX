package com.example.operationx;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.service.quicksettings.Tile;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.constraintlayout.motion.widget.Debug;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Map;

public class GameTile extends View {
    public Activity containerActivity = null;
    private Canvas gameCanvas;
    private Bitmap canvasBitmap;
    private int fixSize,fixWidth,fixHeight;
    private int dir;
    private int xBoundaries;
    public int xPos, yPos;
    private Canvas canvas;

    private Player player;
    private GroundTile groundTile;
    private MapTiles mapTiles;

    private Drawable playerDrawable;
    private ArrayList<Enemy> enemyList;

    public GameTile(Activity currActivity){
        super(currActivity);
        createGameObjects();
        setPreValues();
        containerActivity = currActivity;
        this.setBackgroundColor(0xFFFFFFFF);

        createEnemies();
    }
    private float x1,x2;
    static final int MIN_DISTANCE = 150;

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) <= 100) {
                    dir = 0;
                } else if (deltaX < MIN_DISTANCE && xPos <= 0) {
                    dir = -1;
                } else if (deltaX > MIN_DISTANCE && xPos <= 0) {
                    dir = 1;
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        this.canvas = canvas;
        createGroundTiles();
        //createEnemies();
        updateEnemies();
        createPlayer();
    }

    public int doAction(int actionID){
        System.out.println(enemyList.size());
        if(enemyList.get(0).playerAction(actionID)) {
            enemyList.remove(0);
            return 0;
        }
        return actionID;
    }

    private void createGroundTiles(){
        Drawable tile1 = getResources().getDrawable(R.drawable.ground_tile_1);
        for(int i = 0; i < 20; i++){
            tile1.setBounds(i*fixSize + xPos,yPos/2,
                    i*fixSize + fixSize + xPos,yPos/2+fixHeight);
            groundTile.addGroundTile(containerActivity,tile1.getBounds());
            tile1.draw(canvas);
        }
    }

    private void createPlayer(){
        Drawable playerSprite = getResources().getDrawable(R.drawable.player_1);
        playerSprite.setBounds(200,(yPos/2) - fixHeight,
                200+200,yPos/2);
        playerSprite.draw(this.canvas);
        playerDrawable = playerSprite;
    }

    private void updateEnemies(){
        Drawable enemies = getResources().getDrawable(R.drawable.test_enemy_sprite);

        for(int i = 0; i < 1; i++){
            Rect currBoundaries = enemyList.get(i).getBoundary();
            Rect newCurrBoundaries = new Rect();
            newCurrBoundaries.set(currBoundaries.left + xPos, currBoundaries.top,
                    currBoundaries.right + xPos, currBoundaries.bottom);
            enemyList.get(i).setColliderBoundary(newCurrBoundaries);
            enemies.setBounds(newCurrBoundaries);
            enemies.draw(canvas);
        }
    }
    private void createEnemies(){
        Drawable enemies = getResources().getDrawable(R.drawable.test_enemy_sprite);
        enemyList = new ArrayList<Enemy>();

        for(int i = 1; i < 7; i++){
            enemies.setBounds(700*i + xPos,(yPos/2) - fixHeight,(700*i + fixWidth) + xPos,(yPos/2));
            Enemy newEnemy = new Enemy(0,enemies.getBounds());
            enemyList.add(newEnemy);
        }

    }

    private boolean playerHitEnemy(Drawable player, ArrayList<Enemy> enemyList){
        Enemy currEnemy = enemyList.get(0);
        boolean enemyEncountered = player.getBounds().intersect(currEnemy.getColliderBoundary());
        if(enemyEncountered)
            dir = 0;
        return enemyEncountered;
    }

    public void clearCanvas(){
        canvas.drawColor(Color.WHITE);
        invalidate();
    }

    public void changeXPos(){
        if(!playerHitEnemy(playerDrawable, enemyList) || dir == 1) {
            if (xBoundaries > Math.abs(xPos + (dir * 50))) {
                xPos += (dir * 50);
            }
            if (xPos > 0)
                xPos = 0;
        }
    }


    private void createGameObjects(){
        player = new Player(fixSize,fixWidth,fixHeight);
        groundTile = new GroundTile();

        // Later need to modify again
        mapTiles = new MapTiles(fixSize,fixWidth,fixHeight);
    }

    private void setPreValues(){
        fixSize = 200;
        fixHeight = 200;
        fixWidth = 200;
        fixHeight = 200;
        yPos = 1500;
        xPos = 0;
        dir = 0;
        xBoundaries = fixSize * 14;
    }
}
