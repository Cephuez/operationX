package com.example.operationx.gameplay;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.example.operationx.R;

public class InventoryItems {

    private int levelID;
    private int itemID;
    private Drawable sprite;
    private Activity currActivity;
    public InventoryItems(Activity currActivity, int levelID, int itemID){
        this.levelID = levelID;
        this.itemID = itemID;
        this.currActivity = currActivity;
        setUpItem();
    }

    public Drawable getSprite(){
        return sprite;
    }
    private void setUpItem(){
        if(levelID == 1){
            setUpLevelOne();
        }else{
            setUpLevelTwo();
        }
    }

    private void setUpLevelOne(){
        if(itemID == 1){
            sprite = currActivity.getResources().getDrawable(R.drawable.level_1_primary_attack);
        }else if(itemID == 2){
            sprite = currActivity.getResources().getDrawable(R.drawable.level_1_secondary_attack);
        }else{
            sprite = currActivity.getResources().getDrawable(R.drawable.level_1_third_attack);
        }
    }

    private void setUpLevelTwo(){
        if(itemID == 1){
            sprite = currActivity.getResources().getDrawable(R.drawable.level_2_primary_attack);
        }else if(itemID == 2){
            sprite = currActivity.getResources().getDrawable(R.drawable.level_2_secondary_attack);
        }else{
            sprite = currActivity.getResources().getDrawable(R.drawable.level_2_third_attack);
        }
    }
}
