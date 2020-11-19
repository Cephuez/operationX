package com.example.operationx;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class OperationGameplay extends AppCompatActivity {

    private Drawable tileTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        createTileMap();
    }

    private void createTileMap(){
        //GameTile mTiles = new GameTile(this);
        //mTiles.setLevel();
        LinearLayout gameLayout = findViewById(R.id.game_tile_canvas);

        for(int i = 0; i < 12; i++){
            ImageView tile = new ImageView(this);
            tile.setImageResource(R.drawable.cement_tile_1);
            tile.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            gameLayout.addView(tile);
        }
    }
}
