package com.example.nishanth.datapassing;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

/**
 * Created by nishanth on 2/10/2017.
 */

public class GetAsyncDemoData extends AsyncTask<String,Void,LinkedList<String>> {

    PassData  activity;
    URL u;
    HttpURLConnection con;
    InputStream is;
    LinkedList<String> ls=null;

    @Override
    protected void onPostExecute(LinkedList<String> strings) {

activity.setUPdata(ls);
        super.onPostExecute(strings);
    }

    GetAsyncDemoData(MainActivity data)
    {
        this.activity = data;
    }

    @Override
    protected LinkedList<String> doInBackground(String... params) {
        try {
         ls = new LinkedList<String>();
            ls.add("Tweek 0");
            ls.add("Tweek 1");ls.add("Tweek 2");ls.add("Tweek 3");



        } catch (Exception e) {
            e.printStackTrace();
        }


        return ls;
    }
    public static interface PassData{
       public void setUPdata(LinkedList<String> ls);
    }

}
