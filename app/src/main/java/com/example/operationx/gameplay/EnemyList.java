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
            Enemy newEnemy = new Enemy(0,bounds, currActivity);
            enemyList.add(newEnemy);
        }

        for(int i = 3; i < 7; i++){
            Rect bounds = new Rect(700*i,(gameViewHeight/2) - height,(700*i + width),(gameViewHeight/2));
            Enemy newEnemy = new Enemy(1,bounds, currActivity);
            enemyList.add(newEnemy);
        }

        for(int i = 7; i < 8; i++){
            Rect bounds = new Rect(700*i,(gameViewHeight/2) - height,(700*i + width),(gameViewHeight/2));
            Enemy newEnemy = new Enemy(2,bounds, currActivity);
            enemyList.add(newEnemy);
        }
    }

    private void setLevelTwo(){

    }
}
