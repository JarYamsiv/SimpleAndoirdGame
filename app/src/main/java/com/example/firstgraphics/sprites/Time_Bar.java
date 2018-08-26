package com.example.firstgraphics.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.firstgraphics.main_functions.Drawing_Class;

/**
 * Created by vismay on 2/25/2017.
 */

public class Time_Bar
{
    private float x=60,y=90,ex,h=100;
    private Drawing_Class drawing_class;
    private int width,height;

    public Time_Bar(int width, int height, Drawing_Class drawing_class)
    {
        this.drawing_class=drawing_class;
        this.width=width;
        this.height=height;
        this.ex=this.width-360;
    }

    public void change(double amt)
    {
        if(h+amt>0&&h+amt<100)
        {
            h+=amt;
        }
        else
        {
            if(h+amt<=0)
            {
                h=0;
                drawing_class.gameFail();
            }
            if(h+amt>=100)
            {
                h=100;
            }
        }

    }

    public void fn_Draw(Canvas canvas)
    {
        ex=x+(h/100)*(width-120);
        Paint paint = new Paint();

        paint.setStrokeWidth(20);
        paint.setColor(Color.rgb(100,100,100));
        canvas.drawLine(x,y,width-60,y,paint);
        paint.setColor(Color.rgb(30,52,198));
        canvas.drawLine(x,y,ex,y,paint);
    }

    public void tFull()
    {
        h=100;
    }
}
