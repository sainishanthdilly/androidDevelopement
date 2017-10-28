package com.example.nishanth.okhttpclientdemo;


/**
 * Created by nishanth on 3/27/2017.
 */
/*
Shireen
Nishanth
Group 04
*/


import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class SignupTask extends AsyncTask<String,Void,User> {
    MainActivity signup;
    private final Gson gson = new Gson();
    User loggedUser;

    public SignupTask(MainActivity signup) {
        this.signup=signup;
    }


    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
       // Log.d("userdata",user.toString());
        signup.printSignedInUserDetails(user);
    }

    @Override
    protected User doInBackground(String... params) {

        try {
            RequestBody formBody = new FormBody.Builder()
                    .add("email",params[1]).add("password",params[2]).add("fname",params[3]).add("lname",params[4])
                    .build();
            OkHttpClient client =new OkHttpClient();
            Request request = new Request.Builder()
                    .url(params[0]).post(formBody)
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            User user = gson.fromJson(response.body().string(), User.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
    public  interface LinkSignedInData{
        void printSignedInUserDetails(User user);
    }
}
