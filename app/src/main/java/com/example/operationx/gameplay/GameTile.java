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
    private int xDir,yDir;
    private int xBoundaries;
    private int levelID;
    public int xPos,yPos, gameViewHeight;
    private long startTime;
    private Canvas canvas;

    private Drawable playerDrawable;

    private Player player;
    private Enemy enemy;
    private BackgroundTiles backgroundTiles;
    private GroundTile groundTile;
    private EnemyList enemyList;
    private LevelBoundaries levelBoundaries;
    private LevelControllers levelControllers;

    public GameTile(Activity currActivity, int levelID){
        super(currActivity);
        this.levelID = levelID;
        startTime = 0;
        setPreValues();
        containerActivity = currActivity;
        setLevel();
        this.setBackgroundColor(0xFFFFFFFF);
    }
    private float x1,x2,y1,y2;
    static final int MIN_DISTANCE = 150;

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) <= 100) {
                    xDir = 0;
                } else if (deltaX < MIN_DISTANCE) {
                    xDir = -1;
                } else if (deltaX > MIN_DISTANCE) {
                    xDir = 1;
                }
                break;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                y2 = event.getY();
                float deltaY = y2 - y1;
                if (Math.abs(deltaY) <= 100) {
                    yDir = 0;
                } else if (deltaY < MIN_DISTANCE) {
                    yDir = 1;
                } else if (deltaY > MIN_DISTANCE) {
                    yDir = -1;
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
        levelBoundaries.displayBoundaries(canvas,xPos);
        player.movePlayer(canvas,yPos);
    }

    public int doAction(int actionID){
        if(!enemyList.isEmpty() && enemyList.get(0).playerAction(actionID)
                 && playerHitEnemy(enemyList) && player.doAction(actionID)) {
            enemyList.remove(0);
            return 0;
        }
        return 0;
    }

    public boolean reachedFinishLine(){
        return levelBoundaries.reachFinishLine(player);
    }
    private boolean playerHitEnemy(EnemyList enemyList){
        if(levelID == 1){
            return playerHitEnemyLevelOne();
        }else{
            return playerHitEnemyLevelTwo();
        }
    }

    private boolean playerHitEnemyLevelOne(){
        if(enemyList.isEmpty())
            return false;
        Enemy currEnemy = enemyList.get(0);
        boolean enemyEncountered = player.getBounds().intersect(currEnemy.getColliderBoundary());
        if(enemyEncountered && xDir != -1)
            xDir = 0;
        return enemyEncountered;
    }

    private boolean playerHitEnemyLevelTwo(){
        return true;
    }
    public void clearCanvas(Canvas canvas){
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    public void changeXPos(){
        if(levelID == 1 && !playerHitEnemy(enemyList) || (xDir == -1 && xPos > 500)){
            xPos += 50 * -xDir;
        }else if(levelID == 2){
            xPos -= 50;
            //if(playerHitEnemy(enemyList)){
                //.loseLife();
            //}
        }
    }

    public void changeYPos(){
        if(levelID == 2){
            yPos -= 45 * yDir;
        }
    }

    public int getPlayerScore(){
        return player.getScore();
    }

    private void setLevel(){
        player = new Player(containerActivity,150,fixHeight,gameViewHeight, levelID);
        enemyList = new EnemyList(containerActivity,fixWidth,fixHeight,gameViewHeight, levelID);
        backgroundTiles = new BackgroundTiles(containerActivity,fixWidthBackground,gameViewHeight,levelID);
        groundTile = new GroundTile(containerActivity,fixWidth,fixHeight,gameViewHeight,levelID);
        levelBoundaries = new LevelBoundaries(containerActivity,fixWidth,fixHeight,gameViewHeight,levelID);
    }

    private void setPreValues(){
        fixSize = 300;
        fixHeight = 300;
        fixWidth = 300;
        fixHeight = 300;
        fixWidthBackground = 600;
        gameViewHeight = 1500;
        xPos = 0;
        xDir = 0;
        xBoundaries = fixSize * 18;
    }
}
