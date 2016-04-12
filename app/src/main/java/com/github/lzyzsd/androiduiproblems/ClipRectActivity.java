package com.github.lzyzsd.androiduiproblems;
import android.os.Bundle;
import android.widget.ImageView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
/**
 * Created by RogerRao on 2016/4/12.
 */


/**
 * Demo描述:
 * canvas的save()和restore()以及clipRect()方法测试
 *
 * Demo总结:
 * 1 save()和restore()要配对使用(restore可以比save少,但不能多)
 *   若restore调用次数比save多,会报错
 * 2 canvas剪裁后绘制的东西只能在裁剪区域的范围能才能显示出来
 *
 * 参考资料:
 * http://blog.csdn.net/lonelyroamer/article/details/8264189
 *
 *
 */
public class ClipRectActivity extends Activity {
    private ImageView mImageView;
    private ImageView mClipedImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
    }
    private void init(){
        mImageView=(ImageView) findViewById(R.id.imageView);
        mClipedImageView=(ImageView) findViewById(R.id.clipedImageView);
        //分开测试
        testClip();
        //testSaveAndRestore();
    }

    private void testClip(){
        Bitmap bitmap=Bitmap.createBitmap(200, 200, Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        canvas.drawColor(Color.GREEN);
        Paint paint=new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawText("绿色部分为Canvas剪切前的区域", 20, 50, paint);


        Rect rect=new Rect(10,95,180,140);
        canvas.clipRect(rect);
        canvas.drawColor(Color.YELLOW);
        paint.setColor(Color.BLACK);
        //canvas剪裁后绘制的东西只能在裁剪区域的范围能才能显示出来
        canvas.drawText("黄色部分为Canvas剪切后的区域", 10, 110, paint);
        mImageView.setImageBitmap(bitmap);

        //显示截取后的Bitmap:
        Bitmap newBitmap=Bitmap.createBitmap(bitmap,10,95,170,45);
        mClipedImageView.setImageBitmap(newBitmap);

    }

    private void testSaveAndRestore(){
        Bitmap bitmap=Bitmap.createBitmap(200, 200, Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(25);
        canvas.rotate(15);
        canvas.drawText("Hello Android 1", 20, 30, paint);
        canvas.translate(50, 50);
        //注意:save()方法
        //保存在此save()方法之前的canvas的操作
        //比如:roate(),translate(),clipXXX()
        canvas.save();

        paint.setColor(Color.GREEN);
        paint.setTextSize(15);
        canvas.rotate(60);
        canvas.translate(-20,-20);
        canvas.drawText("Hello Android 2", 20, 60, paint);
        //注意:restore()方法
        //将save()方法之后Canvas的roate(),translate(),clipXXX()的操作清空
        canvas.restore();

        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        canvas.drawText("Hello Android 3", 20, 70, paint);

        mImageView.setImageBitmap(bitmap);

    }
}