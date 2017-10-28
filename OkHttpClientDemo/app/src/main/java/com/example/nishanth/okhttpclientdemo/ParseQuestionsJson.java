package com.example.nishanth.okhttpclientdemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nishanth on 2/10/2017.
 *
 Sai Nishanth Dilly
 Shireen Shaik
 Group 04


*/

public class ParseQuestionsJson {

    public  static User parseJsonQuestions(String s)  {
       User  user = new User();

        try {
            JSONObject root1 = new JSONObject(s);
            user.setStatus(root1.getString("status"));
            //user.set;root1.getString("message");
            user.setToken(root1.getString("data"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return user;

    }


    public  static ArrayList<Channels> parseJsonChannels(String s)  {

        ArrayList<Channels> channelses = new ArrayList<>();

        try {
            JSONObject root1 = new JSONObject(s);
            JSONArray array =root1.getJSONArray("data");
            for(int i=0; i< array.length() ;i++){
                JSONObject ob = array.getJSONObject(i).getJSONObject("channel");
                Channels channel = new Channels(ob.getString("channel_id"), ob.getString("channel_name"));
                channelses.add(channel);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return channelses;

    }


    public static ArrayList<Channels> parseJsonChannelsALL(String string) {



        ArrayList<Channels> channelses = new ArrayList<>();

        try {
            JSONObject root1 = new JSONObject(string);
            JSONArray array =root1.getJSONArray("data");
            for(int i=0; i< array.length() ;i++){
                JSONObject ob = array.getJSONObject(i);
                Channels channel = new Channels(ob.getString("channel_id"), ob.getString("channel_name"));
                channelses.add(channel);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return channelses;


    }
}
