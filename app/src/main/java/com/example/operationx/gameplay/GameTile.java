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
    public Activity containerActivity;
    private int fixSize,fixWidth,fixHeight, fixWidthBackground;
    private int dir;
    private int xBoundaries;
    private int levelID;
    public int xPos, gameViewHeight;
    private Canvas canvas;

    private Drawable playerDrawable;

    private Player player;
    private Enemy enemy;
    private BackgroundTiles backgroundTiles;
    private GroundTile groundTile;
    private EnemyList enemyList;

    public GameTile(Activity currActivity, int levelID){
        super(currActivity);
        this.levelID = levelID;
        setPreValues();
        containerActivity = currActivity;
        setLevel();
        this.setBackgroundColor(0xFFFFFFFF);
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
        groundTile.displayGroundTiles(canvas, xPos);
        backgroundTiles.updateBackground(canvas, xPos);
        enemyList.updateEnemies(canvas, xPos);
        player.movePlayer(canvas);
    }

    public int doAction(int actionID){
        if(!enemyList.isEmpty() && enemyList.get(0).playerAction(actionID) && player.doAction(actionID)) {
            enemyList.remove(0);
            return 0;
        }
        return actionID;
    }

    private boolean playerHitEnemy(EnemyList enemyList){
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
        if(!playerHitEnemy(enemyList) || dir == 1) {
            if (xBoundaries > Math.abs(xPos + (dir * 50))) {
                xPos += (dir * 50);
            }
            if (xPos > 0)
                xPos = 0;
        }
    }

    private void setLevel(){
        player = new Player(containerActivity,150,fixHeight,gameViewHeight, levelID);
        enemyList = new EnemyList(containerActivity,fixWidth,fixHeight,gameViewHeight, levelID);
        backgroundTiles = new BackgroundTiles(containerActivity,fixWidthBackground,gameViewHeight,levelID);
        groundTile = new GroundTile(containerActivity,fixWidth,fixHeight,gameViewHeight,levelID);
    }

    private void setPreValues(){
        fixSize = 300;
        fixHeight = 300;
        fixWidth = 300;
        fixHeight = 300;
        fixWidthBackground = 600;
        gameViewHeight = 1500;
        xPos = 0;
        dir = 0;
        xBoundaries = fixSize * 18;
    }
}
