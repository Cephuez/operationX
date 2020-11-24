package com.example.operationx;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class OperationGameplay extends AppCompatActivity {

    private Drawable tileTest;
    private ImageView level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        createTileMap();
        createPlayer();
    }

    private void createPlayer(){
        Player player = new Player(this,R.drawable.player_1);
    }


    private void createTileMap(){
        //GameTile mTiles = new GameTile(this);
        //mTiles.setLevel();
        ImageView level = findViewById(R.id.game_level);

        //level.setScaleX(1);
        //level.setScaleY(1);
        /*
        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float xPos = level.getX();
                float yPos = level.getY();

                level.setX(xPos + 50);
            }
        });
         */

        level.setImageResource(R.drawable.test_map);
        //level.setLayoutParams(new ViewGroup.LayoutParams(2000,
         //       1000));

    }
}
