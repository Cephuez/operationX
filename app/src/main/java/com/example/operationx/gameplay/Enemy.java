package com.example.operationx.gameplay;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.example.operationx.R;

import java.util.ArrayList;

public class Enemy {

    private int enemyID;
    private ArrayList<Integer> weakness;
    private Rect boundary;
    private Rect colliderBoundary;
    private Activity currActivity;

    public Enemy(int enemyID, Rect boundary, Activity currActivity) {
        this.enemyID = enemyID;
        this.boundary = new Rect(boundary);
        this.currActivity = currActivity;
        weakness = new ArrayList<Integer>();
        colliderBoundary = boundary;
        enemyWeakness();
    }

    public Drawable getDrawable(){
        Drawable enemies = null;
        if(enemyID == 0){
            enemies = currActivity.getResources().getDrawable(R.drawable.enemy1);
        }else if(enemyID == 1){
            enemies = currActivity.getResources().getDrawable(R.drawable.enemy_robot_dog);
        }else{
            enemies = currActivity.getResources().getDrawable(R.drawable.obstacle_fence);
        }
        return enemies;
    }
    public Rect getBoundary(){
        return boundary;
    }

    public void setColliderBoundary(Rect newBoundary){
        this.colliderBoundary = newBoundary;
    }

    public boolean playerAction(int actionID){
        return weakness.contains(actionID);
    }

    public Rect getColliderBoundary(){
        return colliderBoundary;
    }

    public int getEnemyID(){
        return enemyID;
    }

    private void enemyWeakness(){
        if(enemyID == 0) {
            weakness.add(1);
            weakness.add(2);
            weakness.add(3);
        }else if(enemyID == 1){
            weakness.add(2);
            weakness.add(3);
        }else{
            weakness.add(2);
        }
    }


}
