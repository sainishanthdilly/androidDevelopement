package com.example.nishanth.okhttpclientdemo;

import android.os.AsyncTask;
import android.util.Log;

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


public class LoginTask extends AsyncTask<String,Void,User> {
    MainActivity mainActivity;
    private final Gson gson = new Gson();
    User loggedUser;

    public LoginTask(MainActivity mainActivity) {
         this.mainActivity=mainActivity;
    }


    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
        Log.d("userdata",user.toString());
        mainActivity.printLoggedUserDetails(user);
    }

    @Override
    protected User doInBackground(String... params) {

        try {
            RequestBody formBody = new FormBody.Builder()
                    .add("email",params[1]).add("password",params[2])
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
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;

    }
    public  interface LinkData{
        void printLoggedUserDetails(User user);
    }
}
