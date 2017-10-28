package com.example.nishanth.okhttpclientdemo;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
        * Created by nishanth on 3/27/2017.
        */
/*
Shireen
Nishanth
Group 04
*/

public class GetITunesAsync extends AsyncTask<RequestParams,Void,String> {
    MainActivity activity;
    GetITunesAsync(MainActivity activity){
        this.activity = activity;
    }

    @Override
    protected String doInBackground(RequestParams... params) {

        RequestParams rq = params[0];

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(rq.getEncodedURL())
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try {
           return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return null;
    }

    @Override
    protected void onPostExecute(String data) {
        activity.setData(data);

        super.onPostExecute(data);
    }


    static interface GetData {
     public void setData(String data);
    }
}
