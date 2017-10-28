package com.example.nishanth.preclassapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetPersonsAsyncTask().execute("http://rss.cnn.com/rss/cnn_tech.rss");



    }
}
