package com.example.operationx;

import android.graphics.Rect;

import java.util.ArrayList;

public class Enemy {

    private int enemyID;
    private ArrayList<Integer> weakness;
    private Rect boundary;
    private Rect colliderBoundary;

    public Enemy(int enemyID, Rect boundary) {
        this.enemyID = enemyID;
        this.boundary = new Rect(boundary);
        weakness = new ArrayList<Integer>();
        colliderBoundary = boundary;
        enemyWeakness();
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
            weakness.add(2);
        }else if(enemyID == 1){
            weakness.add(1);
            weakness.add(2);
        }else{
            weakness.add(1);
            weakness.add(3);
        }
    }


}
