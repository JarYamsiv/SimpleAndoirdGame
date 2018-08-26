package com.example.firstgraphics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.firstgraphics.main_functions.Drawing_Class;
import com.example.firstgraphics.main_functions.Main_Menu;

public class MainActivity extends Activity
{
    private int width,height;
    public  boolean isRunning=false;
    public Drawing_Class drawing_class;
    public Main_Menu main_menu;
    DemoView demoview;
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        demoview = new DemoView(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(demoview);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        drawing_class= new Drawing_Class(width,height,this);
        main_menu = new Main_Menu(width,height,this);
    }

    protected void onStart() {
        super.onStart();

    }

    private class DemoView extends View{
        public DemoView(Context context){
            super(context);
        }

        @Override protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            if(isRunning)
            {
                drawing_class.move();
                drawing_class.fn_Draw(canvas);
                invalidate();
            }
            else
            {

                main_menu.fn_Draw(canvas);
                main_menu.animate();
                invalidate();
            }

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        if(isRunning)
        {
            drawing_class.command(e);
        }
        else
        {
            main_menu.command(e);
        }
        return true;
    }
}
