package com.example.firstgraphics.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import com.example.firstgraphics.MainActivity;
import com.example.firstgraphics.R;
import com.example.firstgraphics.main_functions.Drawing_Class;

/**
 * Created by vismay on 2/23/2017.
 */

public class Main_Player
{

    MediaPlayer mp;
    private double d=0,tx,ty;
    float hsv[]= new float[3];
    private double sine,cos;
    private Canvas canvas;
    private double x=100,y=100,vx=3,vy=1,ax=0,ay=0,cr;
    private int width,height;
    private Drawing_Class drawing_class;

    public Main_Player(int width, int height, Drawing_Class drawing_class, MainActivity ma)
    {
        mp = MediaPlayer.create(ma, R.raw.snd3);
        this.drawing_class=drawing_class;
        this.width=width;
        this.height=height;
        tx=width/2;
        ty=height/2;
    }

    public void ReInitialize()
    {
        x=100;
        y=100;
        vx=3;
        vy=1;
        tx=width/2;
        ty=height/2;
    }

    public void fn_Draw(Canvas canvas)
    {
        Paint paint = new Paint();



        paint.setAntiAlias(true);
        hsv[0]=(float)(121*(d/700));
        hsv[1]=1;
        hsv[2]=1;
        paint.setColor(Color.HSVToColor(hsv));

        canvas.drawCircle((int)x, (int)y, 60, paint);
        canvas.drawCircle((int)tx, (int)ty, 10, paint);
    }

    public void move()
    {
        this.d = Math.sqrt(Math.pow(x-tx,2)+Math.pow(y-ty,2));
        cr=drawing_class.t*10;
        drawing_class.tb.change(-0.025);
        if(d<200)
        {
            drawing_class.hb.change(-200/d);
            drawing_class.tb.change(+0.2);
        }

        if(d>600)
        {
            drawing_class.hb.change(d*0.00025);
            drawing_class.tb.change(-0.03);
        }

        vx+=ax;
        vy+=ay;
        if(!(x+vx<=width-60&&x+vx>=60))
        {
            mp.start();
            vx*=-(0.5+0.35*(1-Math.exp(-cr)));
        }

        if(!(y+vy<=height-60&&y+vy>=60))
        {
            mp.start();
            vy*=-(0.5+0.35*(1-Math.exp(-cr)));
        }
        y+=vy;
        x+=vx;
    }

    public void command(MotionEvent evnt)
    {
        int X=0,Y=0;
        X = (int) evnt.getX();
        Y = (int) evnt.getY();

        switch (evnt.getAction())
        {


            case MotionEvent.ACTION_MOVE:
            {
                this.tx=X;
                this.ty=Y;

                this.sine = (ty-y)/d;
                this.cos = (tx-x)/d;
                vx+=Math.exp(-1/d)*0.5*cos;
                vy+=Math.exp(-1/d)*0.5*sine;
            }

        }

    }
}
