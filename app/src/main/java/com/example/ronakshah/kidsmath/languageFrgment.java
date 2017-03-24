package com.example.ronakshah.kidsmath;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Locale;

public class languageFrgment extends Fragment {
    public String[] languages = {"English","Hindi","Marathi","Gujarati"};
    public String[] symbols = {"en","hi","mr","gu"};

    private SharedPreferences minCut;
    private SharedPreferences.Editor ed;


    public View onCreateView(LayoutInflater layoutInflater,ViewGroup container,Bundle savedInstanceState)
    {
        minCut = PreferenceManager.getDefaultSharedPreferences(getContext());
        ed = minCut.edit();
        View view = layoutInflater.inflate(R.layout.fragment_language_frgment, container, false);
        final TextView selectText = (TextView)view.findViewById(R.id.select_lang_textview);
        final Button selectButton = (Button)view.findViewById(R.id.select_lang_button);
        ListView selectList = (ListView)view.findViewById(R.id.select_lang_list);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed.commit();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new mainFragment()).commit();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_list_item_multiple_choice,languages);
        selectList.setItemsCanFocus(true);
        selectList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        selectList.setAdapter(adapter);
        selectList.setSelection(3);

        selectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Locale locale = new Locale(symbols[position]);
                locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getContext().getResources().updateConfiguration(config, getContext().getResources().getDisplayMetrics());
                ((MainActivity)getActivity()).getSupportActionBar().setTitle(R.string.app_name);
                selectText.setText(R.string.select_language);
                selectButton.setText(R.string.select_okay);
                getActivity().onConfigurationChanged(config);
                ed.putString("learnLang",symbols[position]);
            }
        });

        return view;
    }
}
