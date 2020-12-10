package com.example.operationx;

/**
 * Represents game level objects to be held in the levelslistfragment
 */
public class GameLevel {
    int level;
    String levelName;
    int characterImage = R.drawable.operation_x_logo;

    public GameLevel(String levelName, int level){
        this.levelName = levelName;
        this.level = level;
    }
}
