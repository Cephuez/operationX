package com.example.operationx;

import android.media.Image;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Player {

    private int spriteID;
    private ImageView playerSprite;
    private OperationGameplay operationGameplay;

    protected int fixSize, fixWidth, fixHeight;
    public Player(OperationGameplay operationGameplay, int spriteID){
        this.spriteID = spriteID;
        this.operationGameplay = operationGameplay;
        SetUpPlayer();
    }

    public Player(int fixSize, int fixWidth, int fixHeight){
        this.fixSize = fixSize;
        this.fixWidth = fixWidth;
        this.fixHeight = fixHeight;
    }

    private void SetUpPlayer(){

    }

}
