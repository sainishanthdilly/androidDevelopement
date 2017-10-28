package com.example.nishanth.okhttpclientdemo;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by nishanth on 3/27/2017.
 */
/*
Shireen
Nishanth
Group 04
*/


public class SendMsgTask extends AsyncTask<String,Void,AddMsg> {
    AddMsg msgdata;
    ChatScreen screen;
    private final Gson gson = new Gson();
    public SendMsgTask(ChatScreen screen){this.screen=screen;}

    @Override
    protected AddMsg doInBackground(String... params) {
        try {
            RequestBody formBody = new FormBody.Builder()
                    .add("msg_text",params[1]).add("msg_time",params[2]).add("channel_id",params[3])
                    .build();
            OkHttpClient client =new OkHttpClient();
            Request request = new Request.Builder()
                    .url(params[0]).addHeader("Authorization","BEARER "+params[4]).post(formBody)
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            AddMsg addMsg = gson.fromJson(response.body().string(), AddMsg.class);
            return addMsg;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(AddMsg addMsg) {
        super.onPostExecute(addMsg);
        screen.getSentACK(addMsg);
    }
    public  interface AddSendInData{
        void getSentACK(AddMsg addMsg);
    }
}
