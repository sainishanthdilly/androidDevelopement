package com.example.nishanth.okhttpclientdemo;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

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


public class MessagesViewTask extends AsyncTask<String,Void,ArrayList<Message>> {
    ChatScreen chatScreen;
    ArrayList<Message> messages=new ArrayList<>();
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public MessagesViewTask(ChatScreen chatScreen) {
        this.chatScreen=chatScreen;
    }
    @Override
    protected ArrayList<Message> doInBackground(String... params) {
        try {
            OkHttpClient client =new OkHttpClient();
            Request request = new Request.Builder()
                    .url(params[0]).addHeader("Authorization","BEARER "+params[1])
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return JsonParser.MessageJsonParser.parsemessages(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Message> messages) {
        super.onPostExecute(messages);
        //Log.d("messagesList",""+messages.size());
        chatScreen.setMsgData(messages);
    }
    interface MessageRetriever {
        void setMsgData(ArrayList<Message> messagelist);
    }
}
