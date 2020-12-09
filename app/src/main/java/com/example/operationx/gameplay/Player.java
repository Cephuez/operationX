package com.example.operationx.gameplay;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.operationx.R;

public class Player {

    private Drawable playerSprite;
    private Activity currActivity;

    private int lives;
    private int width, height, gameViewHeight, levelID, centerView;
    private Inventory inventory;

    public Player(Activity currActivity, int width, int height, int gameViewHeight, int levelID){
        this.currActivity = currActivity;
        this.width = width;
        this.height = height;
        this.gameViewHeight = gameViewHeight;
        this.levelID = levelID;
        lives = 3;
        centerView = 350;
        inventory = new Inventory(currActivity, levelID);
        setPlayerBoundaries();
    }

    public boolean doAction(int actionID){
        return inventory.useItem(actionID);
    }

    public void movePlayer(Canvas canvas, int yPos){
        if(levelID == 2){
            playerSprite.setBounds(-200 + centerView, (gameViewHeight / 2) - height + yPos,
                    -200 + centerView + width * 2, (gameViewHeight / 2) + yPos);
        }
        playerSprite.draw(canvas);
    }

    public int getScore(){
        return inventory.getScore() + lives * 100;
    }

    public Rect getBounds(){
        return playerSprite.getBounds();
    }
    private void setPlayerBoundaries(){
        getPlayerDrawable();
        if(levelID == 1) {
            playerSprite.setBounds(centerView + width, (gameViewHeight / 2) - height,
                    centerView + width + width, gameViewHeight / 2);
        }
    }

    public boolean checkLeftBoundaries(Rect gameBoundaries){
        Rect playerBoundary = playerSprite.getBounds();
        return !playerBoundary.intersect(gameBoundaries) && playerBoundary.left > gameBoundaries.left;
    }

    public boolean checkRightBoundaries(Rect gameBoundaries){
        Rect playerBoundary = playerSprite.getBounds();
        return !playerBoundary.intersect(gameBoundaries) && playerBoundary.right < gameBoundaries.right;
    }

    private void getPlayerDrawable(){
        if(levelID == 1){
            playerSprite = currActivity.getResources().getDrawable(R.drawable.player_11);
        }else {
            playerSprite = currActivity.getResources().getDrawable(R.drawable.player_22);
        }
    }
}
