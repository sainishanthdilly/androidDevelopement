package com.example.nishanth.datapassing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements  GetAsyncDemoData.PassData{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetAsyncDemoData(this).execute("some url....");
    }

    public void setUPdata(LinkedList<String> ls){
        ListView tweek = (ListView) findViewById(R.id.listView1);

        ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplication(),android.R.layout.simple_list_item_1,android.R.id.text1,ls);

        tweek.setAdapter(ar);



    }
}
