package com.example.operationx.gameplay;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

import com.example.operationx.R;
import com.example.operationx.gameplay.Enemy;
import com.example.operationx.gameplay.GroundTile;
import com.example.operationx.gameplay.MapTiles;
import com.example.operationx.gameplay.Player;

import java.util.ArrayList;

public class GameTile extends View {
    public Activity containerActivity = null;
    private int fixSize,fixWidth,fixHeight, fixWidthBackground;
    private int dir;
    private int xBoundaries;
    public int xPos, yPos;
    private Canvas canvas;

    private Drawable playerDrawable;
    private ArrayList<Enemy> enemyList;

    private Player player;
    private Enemy enemy;
    private BackgroundTiles backgroundTiles;
    private GroundTile groundTile;

    public GameTile(Activity currActivity,int levelID){
        super(currActivity);
        setLevel();
        setPreValues();
        containerActivity = currActivity;
        this.setBackgroundColor(0xFFFFFFFF);

        createEnemies();
        //createBackgroundTiles();
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
        createBackgroundTiles();
        createGroundTiles();
        updateEnemies();
        createPlayer();
    }

    public int doAction(int actionID){
        if(!enemyList.isEmpty() && enemyList.get(0).playerAction(actionID)) {
            enemyList.remove(0);
            return 0;
        }
        return actionID;
    }

    private void createBackgroundTiles(){
        Drawable background = getResources().getDrawable(R.drawable.level_1_background);
        for(int i = 0; i < 10; i++){
            background.setBounds(i*fixWidthBackground+xPos, 0,
                    i * fixWidthBackground + fixWidthBackground + xPos, yPos/2);
            background.draw(canvas);
        }
    }

    private void createGroundTiles(){
        Drawable tile1 = getResources().getDrawable(R.drawable.ground_tile_1);
        Drawable tile2 = getResources().getDrawable(R.drawable.ground_tile_2);
        for(int i = 0; i < 20; i++){
            tile1.setBounds(i*fixSize + xPos,yPos/2,
                    i*fixSize + fixSize + xPos,yPos/2+fixHeight);
            groundTile.addGroundTile(containerActivity,tile1.getBounds());
            tile1.draw(canvas);
        }

        for(int i = 1; i < 3; i++) {
            for (int j = 0; j < 20; j++) {
                tile2.setBounds(j * fixSize + xPos, (yPos / 2) + fixHeight * i,
                        j * fixSize + fixSize + xPos, yPos / 2 + fixHeight * i + fixHeight );
                groundTile.addGroundTile(containerActivity, tile1.getBounds());
                tile2.draw(canvas);
            }
        }
    }

    private void createPlayer(){
        player = new Player(fixSize,fixWidth,fixHeight);
        Drawable playerSprite = getResources().getDrawable(R.drawable.player_11);
        playerSprite.setBounds(150,(yPos/2) - fixHeight,
                150+150,yPos/2);
        playerSprite.draw(this.canvas);
        playerDrawable = playerSprite;
    }

    private void updateEnemies(){
        for(int i = 0; i < enemyList.size(); i++){
            Enemy currEnemy = enemyList.get(i);
            Rect currBoundaries = currEnemy.getBoundary();
            Rect newCurrBoundaries = new Rect();
            newCurrBoundaries.set(currBoundaries.left + xPos, currBoundaries.top,
                    currBoundaries.right + xPos, currBoundaries.bottom);
            currEnemy.setColliderBoundary(newCurrBoundaries);
            Drawable enemies = drawEnemy(currEnemy.getEnemyID());
            enemies.setBounds(newCurrBoundaries);
            enemies.draw(canvas);
        }
    }

    private Drawable drawEnemy(int enemyID){
        Drawable enemies = null;
        if(enemyID == 0){
            enemies = getResources().getDrawable(R.drawable.enemy1);
        }else if(enemyID == 1){
            enemies = getResources().getDrawable(R.drawable.enemy_robot_dog);
        }else{
            enemies = getResources().getDrawable(R.drawable.obstacle_fence);
        }
        return enemies;
    }

    private void createEnemies(){
        enemyList = new ArrayList<Enemy>();
        for(int i = 1; i < 3; i++){
            Rect bounds = new Rect(700*i + xPos,(yPos/2) - fixHeight,(700*i + fixWidth) + xPos,(yPos/2));
            Enemy newEnemy = new Enemy(0,bounds);
            enemyList.add(newEnemy);
        }
        for(int i = 3; i < 7; i++){
            Rect bounds = new Rect(700*i + xPos,(yPos/2) - fixHeight,(700*i + fixWidth) + xPos,(yPos/2));
            Enemy newEnemy = new Enemy(1,bounds);
            enemyList.add(newEnemy);
        }

        for(int i = 7; i < 8; i++){
            Rect bounds = new Rect(700*i + xPos,(yPos/2) - fixHeight,(700*i + fixWidth) + xPos,(yPos/2));
            Enemy newEnemy = new Enemy(2,bounds);
            enemyList.add(newEnemy);
        }
    }

    private boolean playerHitEnemy(Drawable player, ArrayList<Enemy> enemyList){
        if(enemyList.isEmpty())
            return false;
        Enemy currEnemy = enemyList.get(0);
        boolean enemyEncountered = player.getBounds().intersect(currEnemy.getColliderBoundary());
        if(enemyEncountered)
            dir = 0;
        return enemyEncountered;
    }

    public void clearCanvas(Canvas canvas){
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
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

    private void setLevel(){
        createPlayer();
        backgroundTiles = new BackgroundTiles();
        groundTile = new GroundTile();

    }

    private void setPreValues(){
        fixSize = 300;
        fixHeight = 300;
        fixWidth = 300;
        fixHeight = 300;
        fixWidthBackground = 600;
        yPos = 1500;
        xPos = 0;
        dir = 0;
        xBoundaries = fixSize * 18;
    }
}
