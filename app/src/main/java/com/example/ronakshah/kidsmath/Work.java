package com.example.ronakshah.kidsmath;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Work extends Activity
{
    Bitmap b;
    protected void onCreate(Bundle savedInstanceState)
     {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        int dig = getIntent().getIntExtra("id",-1);
        TextView t = (TextView) findViewById(R.id.canvas);
        if(dig==0)      t.setText(R.string.zero_name);
        else if(dig==1) t.setText(R.string.one_name);
        else if(dig==2) t.setText(R.string.two_name);
        else if(dig==3) t.setText(R.string.three_name);
        else if(dig==4) t.setText(R.string.four_name);
        else if(dig==5) t.setText(R.string.five_name);
        else if(dig==6) t.setText(R.string.six_name);
        else if(dig==7) t.setText(R.string.seven_name);
        else if(dig==8) t.setText(R.string.eight_name);
        else if(dig==9) t.setText(R.string.nine_name);
        final DrawingView draw = (DrawingView) findViewById(R.id.drawing);
        b = takeSS(R.id.rootView);
        draw.setbitmap(b);
        draw.setVisibility(View.VISIBLE);
    }
    public Bitmap takeSS(int i)
    {
        View v = findViewById(i);
        v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight()); 
        v.setDrawingCacheEnabled(true);
        final Bitmap bitmap = Bitmap.createBitmap(v.getDrawingCache()); // actually taking the screen shot
        v.setDrawingCacheEnabled(false);
        return bitmap;
    }
}