package com.example.operationx.gameplay;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.operationx.R;

public class Player {

    private int spriteID;
    private Drawable playerSprite;
    private Activity currActivity;

    private int width, height, gameViewHeight, levelID;

    public Player(Activity currActivity, int width, int height, int gameViewHeight, int levelID){
        System.out.println(currActivity);
        this.currActivity = currActivity;
        this.width = width;
        this.height = height;
        this.gameViewHeight = gameViewHeight;
        this.levelID = levelID;
        setPlayerBoundaries();
    }

    public void movePlayer(Canvas canvas){
        playerSprite.draw(canvas);
    }

    public Rect getBounds(){
        return playerSprite.getBounds();
    }
    private void setPlayerBoundaries(){
        getPlayerDrawable();
        playerSprite.setBounds(width,(gameViewHeight/2) - height,
                width+width,gameViewHeight/2);
    }

    private void getPlayerDrawable(){
        if(levelID == 1){
            playerSprite = currActivity.getResources().getDrawable(R.drawable.player_11);
        }else {
            playerSprite = currActivity.getResources().getDrawable(R.drawable.operation_x_logo);
        }
    }
}
