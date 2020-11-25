package com.example.operationx;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.service.quicksettings.Tile;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.constraintlayout.motion.widget.Debug;
import androidx.fragment.app.Fragment;

public class GameTile extends View {
    public Activity containerActivity = null;
    private Canvas gameCanvas;
    private Bitmap canvasBitmap;
    private int fixSize;
    private int fixHeight;
    private Canvas canvas;
    private int dir;
    public int xPos = 0;

    public GameTile(Activity currActivity){
        super(currActivity);
        dir = 0;
        containerActivity = currActivity;
        fixSize = 200;
        fixHeight = 1500;
        xPos = 0;
        this.setBackgroundColor(0xFFFFFFFF);
    }

    private float x1,x2;
    static final int MIN_DISTANCE = 150;

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                System.out.println(deltaX);
                if(Math.abs(deltaX) <= 100){
                    dir = 0;
                }else if (deltaX > MIN_DISTANCE) {
                    dir = 1;
                }
                else if(deltaX < MIN_DISTANCE)
                {
                    dir = -1;
                }
                break;
        }
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        this.canvas = canvas;
        Drawable tile1 = getResources().getDrawable(R.drawable.ground_tile_1);
        for(int i = 0; i < 10; i++){
            tile1.setBounds(i*fixSize + xPos,0,i*fixSize + fixSize + xPos,fixHeight);
            tile1.draw(this.canvas);
        }
    }

    public void clearCanvas(){
        canvas.drawColor(Color.WHITE);
        invalidate();
    }
    public void changeXPos(){
        xPos += (dir * 10);
    }
}
