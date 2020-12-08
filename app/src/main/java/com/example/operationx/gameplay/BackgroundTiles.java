package com.example.operationx.gameplay;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.example.operationx.R;

public class BackgroundTiles {
    private Activity currActivity;

    private int fixWidthBackground, gameViewHeight, levelID;

    public BackgroundTiles(Activity currActivity, int fixWidthBackground, int gameViewHeight,int levelID){
        this.currActivity = currActivity;
        this.fixWidthBackground = fixWidthBackground;
        this.gameViewHeight = gameViewHeight;
        this.levelID = levelID;
    }

    public void updateBackground(Canvas canvas, int xPos){
        if(levelID == 1){
            backgroundLevelOne(canvas, xPos);
        }else if(levelID == 2){
            backgroundLevelTwo(canvas,xPos);
        }
    }

    private void backgroundLevelOne(Canvas canvas, int xPos) {
        Drawable background = currActivity.getResources().getDrawable(R.drawable.level_1_background);
        for (int i = 0; i < 10; i++) {
            background.setBounds(i * fixWidthBackground + xPos, 0,
                    i * fixWidthBackground + fixWidthBackground + xPos, gameViewHeight / 2);
            background.draw(canvas);
        }
    }

    private void backgroundLevelTwo(Canvas canvas, int xPos){
        Drawable background = currActivity.getResources().getDrawable(R.drawable.sky_sprite_1);
        for (int i = 0; i < 30; i++) {
            for(int j = 0; j < 2; j++) {
                background.setBounds(i * fixWidthBackground + xPos, (gameViewHeight / 2) * j,
                        i * fixWidthBackground + fixWidthBackground + xPos, (gameViewHeight / 2) + (gameViewHeight / 2) * j);
                background.draw(canvas);
            }
        }
    }
}
