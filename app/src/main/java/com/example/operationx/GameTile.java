package com.example.operationx;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.service.quicksettings.Tile;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

public class GameTile extends View {
    public Activity containerActivity = null;
    private Canvas gameCanvas;
    private Bitmap canvasBitmap;

    public GameTile(Activity currActivity){
        super(currActivity);

        //canvasBitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
        //gameCanvas = new Canvas(canvasBitmap);

        containerActivity = currActivity;
        this.setBackgroundColor(0xFFFFFFFF);
    }

    public void setLevel(){
        CreateTileMap();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        CreateTileMap();
    }

    private void CreateTileMap(){
        Resources res = getResources();
        Bitmap tile = BitmapFactory.decodeResource(res, R.drawable.cement_tile_1);
        gameCanvas = new Canvas(tile.copy(Bitmap.Config.ARGB_8888, true));

        Drawable d = getResources().getDrawable(R.drawable.cement_tile_1);
        d.setBounds(30,30,60,60);
        d.draw(gameCanvas);

    }
}
