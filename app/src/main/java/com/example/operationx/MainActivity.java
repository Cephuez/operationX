package com.example.operationx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
// added comment test from AS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("ASD");
    }

    public void startLevel(View view){
        Intent gameIntent = new Intent(this, OperationGameplay.class);
        startActivity(gameIntent);
    }
}