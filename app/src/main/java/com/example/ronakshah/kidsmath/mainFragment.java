package com.example.ronakshah.kidsmath;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


public class mainFragment extends Fragment 
{
    public View onCreateView(LayoutInflater layoutInflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view = layoutInflater.inflate(R.layout.fragment_main, container, false);
        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(view.getContext()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),Work.class);
                intent.putExtra("id",position);
                startActivity(intent);
            }
        });
        return view;
    }
}
