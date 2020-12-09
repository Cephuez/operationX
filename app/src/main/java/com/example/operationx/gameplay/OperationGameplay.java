package com.example.operationx.gameplay;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.operationx.ActionsFragment;
import com.example.operationx.GameInfoFragment;
import com.example.operationx.HttpPostRequest;
import com.example.operationx.MainMenuFragment;
import com.example.operationx.R;
import com.example.operationx.SettingsFragment;

import java.util.Timer;
import java.util.TimerTask;

public class OperationGameplay extends AppCompatActivity {

    private Drawable tileTest;
    private ImageView level;
    private GameTile gameView;
    private Canvas canvas;
    private Activity activty;

    private ActionsFragment af;
    private final String[] endGameOptions = {"Main Menu", "Levels List"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activty = this;
        setContentView(R.layout.game_layout);
        af = new ActionsFragment();
        GameInfoFragment gif = new GameInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("LEVEL_FRAG_INT", getIntent().getExtras().getInt("LEVEL_INT"));
        gif.setArguments(bundle);
        createTileMap(getIntent().getExtras().getInt("LEVEL_INT"));
        beginPlayerMovement();

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.game_info_panel, gif);
        transaction.replace(R.id.action_layout, af);
        transaction.commit();
        System.out.println("Current LEVEL_INT: " + getIntent().getExtras().getInt("LEVEL_INT"));
    }

    private void createTileMap(int levelID){
        af.getLevelID(levelID);
        gameView = new GameTile(this,levelID);
        FrameLayout gameLayout = findViewById(R.id.game_view);

        Bitmap result = Bitmap.createBitmap(500,500,Bitmap.Config.ARGB_8888);
        canvas = new Canvas(result);

        gameView.draw(canvas);
        gameView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1500));

        gameLayout.addView(gameView);
    }

    private void startLevel(int levelID){
        gameView = new GameTile(this,levelID);
    }

    private void beginPlayerMovement() {
        SharedPreferences sharedPref = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        Integer fps = sharedPref.getInt(getString(R.string.saved_fps_key), 1);
        System.out.println("FPS = " + fps);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        gameView.clearCanvas(canvas);
                        gameView.draw(canvas);
                        af.playerAction(gameView);
                        gameView.changeXPos();
                        gameView.changeYPos();
                        if(gameView.reachedFinishLine() || gameView.playerDead()) {
                            HttpPostRequest request = new HttpPostRequest();
                            request.execute("TestX", 1200);
                            cancel();
                        }
                    }
                });
            }
        }, 0, 75);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
