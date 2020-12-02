package com.example.operationx.gameplay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.operationx.R;

import java.util.ArrayList;

public class GroundTile {

    private ArrayList<Drawable> groundTiles;

    public GroundTile(){

        groundTiles = new ArrayList<Drawable>();
    }

    public void addGroundTile(Context context, Rect tileBounds){
        Drawable floorTile = context.getResources().getDrawable(R.drawable.ground_tile_1);
        floorTile.setBounds(new Rect(tileBounds));
        groundTiles.add(floorTile);
    }

    public ArrayList<Drawable> getGroundTiles(){
        return groundTiles;
    }
}
