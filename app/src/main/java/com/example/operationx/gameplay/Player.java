package com.example.operationx.gameplay;

import android.widget.ImageView;

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
