package com.github.lzyzsd.androiduiproblems;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by bruce on 2/27/16.
 */
public class BusyOnDrawView extends View {

    private Paint mPaint;

    public BusyOnDrawView(Context context) {
        super(context);
    }

    public BusyOnDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BusyOnDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BusyOnDrawView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        for (int i = 0; i < 1000; i++) {
//            System.out.println("canvas = [" + canvas + "]" + i);
//        }
        //should not do something that is time consuming.
        if(mPaint==null)
        {
            mPaint = new Paint();
            mPaint.setColor(Color.RED);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(4);
        }

        int radius = Math.min(getWidth(), getHeight()) / 2;
        canvas.drawCircle(getWidth()/2, getHeight()/2, radius, mPaint);


    }
}
