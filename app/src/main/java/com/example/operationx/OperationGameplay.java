package com.example.operationx;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class OperationGameplay extends AppCompatActivity {

    private Drawable tileTest;
    private ImageView level;
    private GameTile gameView;
    private Canvas canvas;

    private ActionsFragment af;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        af = new ActionsFragment();
        GameInfoFragment gif = new GameInfoFragment();


        createTileMap();
        beginPlayerMovement();
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.game_info_panel, gif);
        transaction.replace(R.id.action_layout, af);

        transaction.commit();
    }



    private void createTileMap(){
        gameView = new GameTile(this);

        FrameLayout gameLayout = findViewById(R.id.game_view);
        /*
        Bitmap result = Bitmap.createBitmap(500,500,Bitmap.Config.ARGB_8888);
        canvas = new Canvas(result);

        gameView.draw(canvas);
        gameView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1500));

         */
        Bitmap result = Bitmap.createBitmap(100,100,Bitmap.Config.ARGB_8888);
        canvas = new Canvas(result);

        gameView.draw(canvas);
        gameView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1000));
        gameLayout.addView(gameView);
    }

    private void beginPlayerMovement(){
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameView.changeXPos();
                gameView.clearCanvas(canvas);
                gameView.draw(canvas);
                af.playerAction(gameView);
            }
        }, 0, 100);//put here time 1000 milliseconds=1 second
    }
}
