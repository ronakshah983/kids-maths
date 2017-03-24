package com.example.ronakshah.kidsmath;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter 
{

    private Context mContext;

    public Integer[] ids = 
    {
        R.drawable.zero,R.drawable.one,R.drawable.two,R.drawable.three,
        R.drawable.four,R.drawable.five,R.drawable.six,
        R.drawable.seven,R.drawable.eight,R.drawable.nine,
    };

    public ImageAdapter(Context c) {    mContext = c;   }
    @Override
    public int getCount() { return ids.length; }
    @Override
    public Object getItem(int position) {   return null;    }
    @Override
    public long getItemId(int position) {   return 0;   }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView==null)
        {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(125,125));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(15,15,15,15);
        }
        else
        {
            imageView = (ImageView)convertView;
        }
        imageView.setImageResource(ids[position]);
        return imageView;
    }
}
