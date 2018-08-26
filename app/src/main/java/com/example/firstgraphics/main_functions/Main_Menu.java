package com.example.firstgraphics.main_functions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.firstgraphics.MainActivity;
import com.example.firstgraphics.sprites.Health_Bar;
import com.example.firstgraphics.sprites.Main_Player;
import com.example.firstgraphics.sprites.Time_Bar;

/**
 * Created by vismay on 2/25/2017.
 */

public class Main_Menu
{
    private MainActivity ma;
    private int width,height;
    public int animState=0;
    public double animTime=0.0;
    private int y=0,dy=1;

    public Main_Menu(int width,int height,MainActivity ma)
    {
        this.ma=ma;
        this.width=width;
        this.height=height;
    }

    public void animate()
    {
        y+=dy;
        if(y>30||y<-30)
        {
            dy*=-1;
        }

        if(animState!=0)
        {
            animTime--;
        }

        if(animTime<=0&&animState!=0)
        {
            animState=0;
            ma.isRunning = true;
            ma.drawing_class.ReInitialize();
        }
    }
    public void fn_Draw(Canvas canvas)
    {
        Paint paint = new Paint();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);

        if(animState==0)
        {
            paint.setColor(Color.rgb(25,25,25));
            paint.setTextSize(75.0f);
            paint.setFakeBoldText(true);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Play!!",(int)(width/2),(int)(height/2-100+y),paint);
            canvas.drawText("HighScore",(int)(width/2),(int)(height/2+100-y),paint);
        }
        else
        {

                    if(this.animTime>=0.0)
                    {
                        paint.setColor(Color.rgb(25, 25, 25));
                        paint.setTextSize(75.0f);
                        paint.setFakeBoldText(true);
                        paint.setTextAlign(Paint.Align.CENTER);
                        canvas.drawText("Play!!",(int)(width/2),(int)(height/2-100+y),paint);
                        canvas.drawText("HighScore",(int)(width/2),(int)(height/2+100),paint);
                        canvas.drawCircle((float)width/2,(float)height/2,(float)(100*(60-animTime)),paint);
                        this.animTime-=1;
                    }
        }
    return;
    }



    public void command(MotionEvent evnt)
    {
        int X=0,Y=0;
        X = (int) evnt.getX();
        Y = (int) evnt.getY();

        switch (evnt.getAction())
        {


            case MotionEvent.ACTION_DOWN:
            {
                if((X<width/2+100&&X>width/2-100)&&(Y<y+height/2-100+60&&Y>y+height/2-100-60))
                {
                    if(animState!=1)
                    {
                        this.animTime=120;
                    }
                    animState=1;

                    //ma.isRunning=true;
                    //ma.drawing_class.ReInitialize();
                    return;
                }
            }

        }

    }

}
