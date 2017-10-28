package com.example.nishanth.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   // String [] colors = {"RED","BLUE","GREEN","BLACK","WHITE"};
    ArrayList<ColorAc> colors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView12  = (ListView) findViewById(R.id.listView1);
        colors = new ArrayList<>();
        colors.add(new ColorAc("RED","0X0FFF"));
        colors.add(new ColorAc("GREEN","0X0FFF"));
        colors.add(new ColorAc("BLACK","0X0FF1"));
        colors.add(new ColorAc("BROWN","0X0FFE"));



        //ArrayAdapter<ColorAc> adapter = new ArrayAdapter<ColorAc>(this,android.R.layout.simple_list_item_1,colors);
        ColorAdapter adapter = new ColorAdapter(this,R.layout.row_layout_item,colors);
        listView12.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        adapter.add(new ColorAc("Maganta","0X0FFD"));
        adapter.insert(new ColorAc("Pink","0X0FFD"),0);


       listView12.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Log.d("Demo","Item Selected , "+position+" , value "+colors.get(position).toString());
           }
       });

    }
}
