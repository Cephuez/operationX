package com.example.operationx;

public class GameLevel {

    int level;
    String levelName;
    int difficulty;
    int characterImage;

    public GameLevel(String levelName, int level){
        if(level == 1){
            characterImage = R.drawable.level_1_view;
        }else{
            characterImage = R.drawable.level_2_view;
        }
        this.levelName = levelName;
        this.level = level;
    }
}
