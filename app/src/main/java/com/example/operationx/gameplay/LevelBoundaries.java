package com.example.operationx.gameplay;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.ArrayAdapter;

import com.example.operationx.R;

import java.util.ArrayList;

public class LevelBoundaries {
    private Activity currActivity;
    private int width, height, gameViewHeight, levelID;
    private ArrayList<Rect> endBoundaries;

    public LevelBoundaries(Activity currActivity, int width, int height, int gameViewHeight, int levelID){
        this.currActivity = currActivity;
        this.width = width;
        this.height = height;
        this.gameViewHeight = gameViewHeight;
        this.levelID = levelID;
    }

    public ArrayList<Rect> getEndBoundariesList(){
        return endBoundaries;
    }

    public void displayBoundaries(Canvas canvas, int xPos){
        endBoundaries = new ArrayList<Rect>();
        if(levelID == 1){
            backgroundLevelOne(canvas, xPos);
        }else if(levelID == 2){
            backgroundLevelTwo(canvas,xPos);
        }
    }

    // Add the boundaries to the level then it should be good xd.
    private void backgroundLevelOne(Canvas canvas, int xPos){
        Drawable endBackground = currActivity.getResources().getDrawable(R.drawable.ground_tile_2);
        for(int i = 0; i < 5; i++) {
            for (int j = -5; j < 0; j++) {
                endBackground.setBounds(j * width + xPos, height * i,
                        j * width + width + xPos, height * i + height );
                endBackground.draw(canvas);
            }
        }

        for(int i = 0; i < 5; i++) {
            for (int j = 20; j < 25; j++) {
                endBackground.setBounds(j * width + xPos, height * i,
                        j * width + width + xPos, height * i + height );
                endBackground.draw(canvas);
            }
        }

        endBoundaries.add(new Rect(-width + xPos, 0, -width + width + xPos, gameViewHeight));
        endBoundaries.add(new Rect(20 * width + xPos, 0, 20 * width + width + xPos, gameViewHeight));
    }

    public boolean playerOnEndBoundaries(Rect playerBoundaries){
        for(int i = 0; i < endBoundaries.size(); i++){
            if(endBoundaries.get(i).intersect(playerBoundaries))
                return true;
        }
        return false;
    }

    private void backgroundLevelTwo(Canvas canvas, int xPos){

    }
}
