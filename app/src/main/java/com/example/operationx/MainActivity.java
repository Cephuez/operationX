package com.example.operationx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.operationx.gameplay.OperationGameplay;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<GameLevel> levels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.cephy_test_layout);
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
    }
}



