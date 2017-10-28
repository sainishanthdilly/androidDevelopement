package com.example.nishanth.homework03;

/*
 Sai Nishanth Dilly
 Shireen Shaik
 Group 04

  */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Hashtable;
import java.util.Iterator;

import java.util.HashMap;
import java.util.Set;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        LinearLayout l = (LinearLayout)findViewById(R.id.LinearRes);

        HashMap<String,Long> h = (HashMap<String, Long>) getIntent().getExtras().get("Res");

        Set<String> s =h.keySet();
        Iterator<String> it = s.iterator();

        while(it.hasNext()){
            String key= it.next();
            TextView t = new TextView(this);
            t.setText(key+": "+(h.get(key)).toString());
            l.addView(t);

        }

        Button fn = (Button)findViewById(R.id.buttonfinish);
        fn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Results.this,MainActivity.class);
                startActivity(i);
            }
        });








    }
}
