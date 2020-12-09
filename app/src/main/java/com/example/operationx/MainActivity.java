package com.example.operationx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.operationx.gameplay.OperationGameplay;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<GameLevel> levels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.cephy_test_layout);
        playMusic();
        setContentView(R.layout.activity_main);

        MainMenuFragment mmFrag = new MainMenuFragment();
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_layout,
                mmFrag);
        transaction.commit();

    }


    public void startGame(View view){
        Intent intent = new Intent(this, OperationGameplay.class);
        startActivity(intent);
        playMusic();


    }

    public void playMusic(){

        final MediaPlayer music = MediaPlayer.create(this,R.raw.blazer_rail);
        music.setVolume(1,1);
        music.setLooping(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                music.start();
            }
        }).start();
    }
}



