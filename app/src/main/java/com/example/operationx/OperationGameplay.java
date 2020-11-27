package com.example.operationx;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.Timer;
import java.util.TimerTask;

public class OperationGameplay extends AppCompatActivity {

    private Drawable tileTest;
    private ImageView level;
    private GameTile gameView;
    private Canvas canvas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        createCanvas();
        createTileMap();
        createPlayer();
        beginPlayerMovement();
    }

    private void createCanvas(){

    }
    private void createPlayer(){
        Player player = new Player(this,R.drawable.player_1);
    }


    private void createTileMap(){
        gameView = new GameTile(this);

        LinearLayout gameLayout = findViewById(R.id.game_view);
        Bitmap result = Bitmap.createBitmap(500,500,Bitmap.Config.ARGB_8888);
        canvas = new Canvas(result);

        gameView.draw(canvas);
        gameView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1500));
        gameLayout.addView(gameView);
    }

    private void beginPlayerMovement(){
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameView.changeXPos();
                gameView.clearCanvas();
                gameView.draw(canvas);
            }
        }, 0, 100);//put here time 1000 milliseconds=1 second
    }
}
