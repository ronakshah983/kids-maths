package com.example.ronakshah.kidsmath;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==null)
        {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String learnLang = prefs.getString("learnLang","none");
            if(learnLang.equals("none"))
            {
                getSupportFragmentManager().beginTransaction().add(R.id.container,new languageFrgment()).commit();
            }
            else
            {
                Locale locale = new Locale(learnLang);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getResources().updateConfiguration(config,getResources().getDisplayMetrics());
                getSupportActionBar().setTitle(R.string.app_name);
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new mainFragment()).commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        int id = item.getItemId();
        switch(id)
        {
            case R.id.action_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new languageFrgment()).commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
