package com.example.operationx.gameplay;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class EnemyList {
    private Activity currActivity;
    private ArrayList<Enemy> enemyList;
    private int width, height, gameViewHeight, levelID;

    public EnemyList(Activity currActivity, int width, int height, int gameViewHeight, int levelID){
        this.currActivity = currActivity;
        this.width = width;
        this.height = height;
        this.gameViewHeight = gameViewHeight;
        this.levelID = levelID;
        enemyList = new ArrayList<Enemy>();
        setUpList();
    }

    public Enemy get(int i ){
        return enemyList.get(i);
    }
    public boolean isEmpty(){
        return enemyList.isEmpty();
    }
    public void remove(int index){
        enemyList.remove(index);
    }
    public int size(){
        return enemyList.size();
    }

    public void updateEnemies(Canvas canvas, int xPos){
        for(int i = 0; i < enemyList.size(); i++){
            Enemy currEnemy = enemyList.get(i);
            Rect currBoundaries = currEnemy.getBoundary();
            Rect newCurrBoundaries = new Rect();
            newCurrBoundaries.set(currBoundaries.left + xPos, currBoundaries.top,
                    currBoundaries.right + xPos, currBoundaries.bottom);
            currEnemy.setColliderBoundary(newCurrBoundaries);
            Drawable enemies = currEnemy.getDrawable();
            enemies.setBounds(newCurrBoundaries);
            enemies.draw(canvas);
        }
    }

    private void setUpList(){
        if(levelID == 1)
            setLevelOne();
        else if(levelID == 2){
            setLevelTwo();
        }
    }

    private void setLevelOne(){
        for(int i = 1; i < 3; i++){
            Rect bounds = new Rect(700*i,(gameViewHeight/2) - height,(700*i + width),(gameViewHeight/2));
            Enemy newEnemy = new Enemy(levelID,0,bounds, currActivity);
            enemyList.add(newEnemy);
        }

        for(int i = 3; i < 7; i++){
            Rect bounds = new Rect(700*i,(gameViewHeight/2) - height,(700*i + width),(gameViewHeight/2));
            Enemy newEnemy = new Enemy(levelID,1,bounds, currActivity);
            enemyList.add(newEnemy);
        }

        for(int i = 7; i < 8; i++){
            Rect bounds = new Rect(700*i,(gameViewHeight/2) - height,(700*i + width),(gameViewHeight/2));
            Enemy newEnemy = new Enemy(levelID,2,bounds, currActivity);
            enemyList.add(newEnemy);
        }
    }

    private void setLevelTwo(){
        Rect bounds = new Rect(700*1,0,(700*1 + width),(gameViewHeight/4));
        Enemy newEnemy = new Enemy(levelID,0,bounds, currActivity);
        enemyList.add(newEnemy);

        bounds = new Rect(700*2,20,(700*2 + width),(gameViewHeight/4)+20);
        newEnemy = new Enemy(levelID,0,bounds, currActivity);
        enemyList.add(newEnemy);

        bounds = new Rect(700*3,50,(700*3 + width),(gameViewHeight/4)+50);
        newEnemy = new Enemy(levelID,0,bounds, currActivity);
        enemyList.add(newEnemy);

        bounds = new Rect(700*4,200,(700*4 + width),(gameViewHeight/4) + 200);
        newEnemy = new Enemy(levelID,0,bounds, currActivity);
        enemyList.add(newEnemy);

        bounds = new Rect(700*5,300,(700*5  + width),(gameViewHeight/4) + 300);
        newEnemy = new Enemy(levelID,0,bounds, currActivity);
        enemyList.add(newEnemy);

        bounds = new Rect(700*6,250,(700*6 + width),(gameViewHeight/4) + 250);
        newEnemy = new Enemy(levelID,1,bounds, currActivity);
        enemyList.add(newEnemy);

        bounds = new Rect(700*7,(gameViewHeight/4),(700*7 + width),(gameViewHeight*2/4));
        newEnemy = new Enemy(levelID,1,bounds, currActivity);
        enemyList.add(newEnemy);

        bounds = new Rect(700*8,(gameViewHeight*2/4),(700*8 + width),(gameViewHeight*3/4));
        newEnemy = new Enemy(levelID,1,bounds, currActivity);
        enemyList.add(newEnemy);

        bounds = new Rect(700*9,(gameViewHeight * 2/4),(700*9 + width),(gameViewHeight*3/4));
        newEnemy = new Enemy(levelID,1,bounds, currActivity);
        enemyList.add(newEnemy);

        bounds = new Rect(700*10,(gameViewHeight*2/4)+100,(700*10 + width),(gameViewHeight*2/4) + 100 + (gameViewHeight/4));
        newEnemy = new Enemy(levelID,2,bounds, currActivity);
        enemyList.add(newEnemy);

        bounds = new Rect(700*11,(gameViewHeight*2/4),(700*11 + width),(gameViewHeight*3/4));
        newEnemy = new Enemy(levelID,2,bounds, currActivity);
        enemyList.add(newEnemy);

        bounds = new Rect(700*12,(gameViewHeight*3/4),(700*12 + width),gameViewHeight);
        newEnemy = new Enemy(levelID,2,bounds, currActivity);
        enemyList.add(newEnemy);
    }
}
