package com.example.firstgraphics.main_functions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import com.example.firstgraphics.MainActivity;
import com.example.firstgraphics.R;
import com.example.firstgraphics.sprites.Health_Bar;
import com.example.firstgraphics.sprites.Main_Player;
import com.example.firstgraphics.sprites.Time_Bar;

/**
 * Created by vismay on 2/22/2017.
 */

public class Drawing_Class
{
    MediaPlayer gf;
    private MainActivity ma;
    public Main_Player mp;
    public Health_Bar hb;
    public Time_Bar tb;
    public double t=0;


    public Drawing_Class(int width,int height,MainActivity ma)
    {
        gf = MediaPlayer.create(ma, R.raw.hd);
        this.ma=ma;
        mp = new Main_Player(width,height,this,this.ma);
        hb= new Health_Bar(width,height,this);
        tb = new Time_Bar(width,height,this);
    }

    public void ReInitialize()
    {
        t=0;
        hb.hFull();
        mp.ReInitialize();
        tb.tFull();
    }

    public void fn_Draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(25,25,25));
        canvas.drawPaint(paint);

        mp.fn_Draw(canvas);
        hb.fn_Draw(canvas);
        tb.fn_Draw(canvas);

        t+=1/60.0;
        paint.setColor(Color.WHITE);
        paint.setTextSize(30.0f);
        canvas.drawText(String.valueOf((int)(10*t)),15,25,paint);
    }

    public void move()
    {
        mp.move();
    }

    public void command(MotionEvent evnt)
    {
        mp.command(evnt);

    }

    public void gameFail()
    {
        gf.start();
        ma.main_menu.animState=0;
        ma.isRunning=false;
    }

}
