package com.example.operationx;

import android.graphics.Rect;

public class Enemy {

    private Rect boundary;

    public Enemy(Rect boundary) {
        this.boundary = new Rect(boundary);
        //this.boundary = boundary;
    }

    public Rect getBoundary(){
        return boundary;
    }
}
