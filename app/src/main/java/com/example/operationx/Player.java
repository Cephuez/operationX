package com.example.operationx;

import android.media.Image;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Player {

    private int spriteID;
    private ImageView playerSprite;
    private OperationGameplay operationGameplay;
    public Player(OperationGameplay operationGameplay, int spriteID){
        this.spriteID = spriteID;
        this.operationGameplay = operationGameplay;
        SetUpPlayer();
    }

    private void SetUpPlayer(){
        ImageView playerView = operationGameplay.findViewById(R.id.player_sprite);
        playerView.setImageResource(R.drawable.player_1);
    }

}
