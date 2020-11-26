package com.example.operationx;

public class GameLevel {

    int level;
    String levelName;
    int difficulty;
    int characterImage = R.drawable.operation_x_logo;

    public GameLevel(String levelName, int level){
        this.levelName = levelName;
        this.level = level;
    }
}
