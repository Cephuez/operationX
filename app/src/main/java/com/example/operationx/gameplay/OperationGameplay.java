package com.example.operationx.gameplay;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.operationx.ActionsFragment;
import com.example.operationx.GameInfoFragment;
import com.example.operationx.HttpPostRequest;
import com.example.operationx.LevelsListFragment;
import com.example.operationx.R;

import java.util.Timer;
import java.util.TimerTask;

public class OperationGameplay extends AppCompatActivity {
    private Drawable tileTest;
    private ImageView level;
    private GameTile gameView;
    private Canvas canvas;
    private Activity activty;
    private final String[] endGameOptions = {"Next Level"};
    private ActionsFragment af;
    private GameInfoFragment gif;

    public OperationGameplay() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playMusic();
        activty = this;
        setContentView(R.layout.game_layout);
        af = new ActionsFragment();
        gif = new GameInfoFragment();
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
                        if(!gif.isPaused()) {
                            gameView.clearCanvas(canvas);
                            gameView.draw(canvas);
                            af.playerAction(gameView);
                            gameView.changeXPos();
                            gameView.changeYPos();
                        }
                        if(gameView.reachedFinishLine()) {
                            music.pause();
                            final AlertDialog.Builder builder = new AlertDialog.Builder(OperationGameplay.this);

                            builder.setItems(endGameOptions, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(which == 0){
                                        System.out.println("Go to Next Level");
                                        LevelsListFragment llf = new LevelsListFragment();
                                        FragmentManager fm = getSupportFragmentManager();
                                        fm.beginTransaction()
                                                .replace(R.id.game_layout, llf)
                                                .addToBackStack(null)
                                                .commit();
                                    }
                                }
                            });
                            cancel();
                            AlertDialog.Builder scoreName = new AlertDialog.Builder(OperationGameplay.this);
                            scoreName.setTitle("Score: " + gameView.getPlayerScore());
                            scoreName.setMessage("Enter Name for score:");
                            final EditText input = new EditText(OperationGameplay.this);
                            scoreName.setView(input);

                            scoreName.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    String userName = input.getText().toString();
                                    HttpPostRequest request = new HttpPostRequest();
                                    request.execute(userName, gameView.getPlayerScore());
                                    AlertDialog alertDialog = builder.show();
                                    alertDialog.setCanceledOnTouchOutside(false);
                                    alertDialog.setCancelable(false);
                                    return;
                                }
                            });
                            scoreName.show();
                        }
                    }
                });
            }
        }, 0, 100 - 25 * fps);
    }

    private MediaPlayer music;
    public void playMusic(){
        SharedPreferences sharedPref = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        float volumeNumber = Float.valueOf(sharedPref.getInt(getString(R.string.saved_volume_key), 1));
        if(volumeNumber == 1)
            volumeNumber = 100;

        music = MediaPlayer.create(this,R.raw.blazer_rail);
        music.setVolume( volumeNumber/100, volumeNumber/100);
        music.setLooping(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                music.start();
            }
        }).start();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        music.pause();
    }
}
