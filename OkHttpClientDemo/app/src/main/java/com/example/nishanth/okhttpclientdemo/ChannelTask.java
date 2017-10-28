package com.example.nishanth.okhttpclientdemo;

import android.os.AsyncTask;

import java.io.IOException;
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


public class ChannelTask extends AsyncTask<String,Void,ArrayList<Channels>> {

    SubscriptionActivity activity;
    ChannelTask(SubscriptionActivity activity){
        this.activity =activity;
    }

    @Override
    protected ArrayList<Channels> doInBackground(String... params) {
        try {
           // RequestBody formBody = new FormBody.Builder()
            //        .add("email",params[1]).add("password",params[2]).add("fname",params[3]).add("lname",params[4])
             //       .build();
            OkHttpClient client =new OkHttpClient();
            Request request = new Request.Builder()
                    .url(params[0]).addHeader("Authorization", "BEARER "+params[1]).get()
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            ArrayList<Channels> channelses = ParseQuestionsJson.parseJsonChannels(response.body().string());
            return channelses;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Channels> channelsArrayList) {
        super.onPostExecute(channelsArrayList);
        activity.printSubChannelData(channelsArrayList);

    }

    public  interface SubChannelData{
        void printSubChannelData(ArrayList<Channels> channelsArrayList);
    }

}
