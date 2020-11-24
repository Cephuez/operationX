package com.example.operationx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.logging.Level;

public class MainActivity extends AppCompatActivity {
    ArrayList<GameLevel> levels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameLevel level1 = new GameLevel("Cat Level", 1);
        GameLevel level2 = new GameLevel("Plane Level", 2);
        GameLevel level3 = new GameLevel("Pug Level", 3);

        levels.add(level1);
        levels.add(level2);
        levels.add(level3);


    }

    public void showLevels(View view){
        LevelsListFragment llf = new LevelsListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.levelsList, llf, "LevelsListFrag")
                .addToBackStack(null)
                .commit();


    }

    public void startLevel(View view){
        Intent gameIntent = new Intent(this, OperationGameplay.class);
        startActivity(gameIntent);
    }
}