package com.example.ronakshah.kidsmath;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("NewApi")
public class DrawingView extends View {
    private Path path;
    private Paint drawPaint,canvasPaint;
    private Canvas drawCanvas;
    private Bitmap bitmap;
    private Context context;
    private int wi,he;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        wi = metrics.widthPixels;
        he = metrics.heightPixels;

        bitmap = Bitmap.createBitmap(wi,he,Bitmap.Config.ARGB_8888);
        setupDrawing();
    }
    public void setbitmap(Bitmap bi)
    {
        int x=(wi-bi.getWidth())/2,i,j;
        for(i=x;i<(x+bi.getWidth());i++)
            for(j=0;j<bi.getHeight();j++)
                bitmap.setPixel(i,j,bi.getPixel(i-x,j));
        drawCanvas = new Canvas(bitmap);
    }
    public void setupDrawing()
    {
        path = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(Color.GREEN);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(15);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }
    protected void onDraw(Canvas c)
    {
        c.drawBitmap(bitmap, 0, 0, canvasPaint);
        c.drawPath(path,drawPaint);
    }
    public boolean onTouchEvent(MotionEvent event)
    {
        float cx = event.getX();
        float cy = event.getY();
        int x= (int)(cx*bitmap.getWidth() /wi);
        int y= (int)(cy*bitmap.getHeight()/he);

        if(bitmap.getPixel(x,y)==0) {
//            Toast.makeText(getContext(),"Outside",Toast.LENGTH_SHORT).show();
            ((Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(50);
        }
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:   path.moveTo(cx,cy);break;
            case MotionEvent.ACTION_MOVE:   path.lineTo(cx,cy);break;
            case MotionEvent.ACTION_UP:     drawCanvas.drawPath(path,drawPaint);path.reset();break;
            default:return false;
        }
        invalidate();
        return true;
    }
}