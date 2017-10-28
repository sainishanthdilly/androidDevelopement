package com.example.nishanth.okhttpclientdemo;


/**
 * Created by nishanth on 3/27/2017.
 */
/*
Shireen
Nishanth
Group 04
*/


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {

    ArrayList<Message> messageslist = new ArrayList<>();
    JSONObject root = null;

    static  public class MessageJsonParser {

        static ArrayList<Message> parsemessages(String in) throws JSONException {

            ArrayList<Message> messageslist = new ArrayList<>();
            JSONObject root = new JSONObject(in);
            JSONArray messagesJSONArray = root.getJSONArray("messages");

            for(int i = 0; i< messagesJSONArray.length();i++)
            {
                JSONObject rootObj = messagesJSONArray.getJSONObject(i);
                String fname = rootObj.getString("UserFname");
                String lname = rootObj.getString("UserLname");
                String id = rootObj.getString("Id");
                String comment = rootObj.getString("Comment");
                String filethumbnailid = rootObj.getString("FileThumbnailId");
                String type = rootObj.getString("Type");
                String createdat = rootObj.getString("CreatedAt");
                Message message = new Message(fname,lname,id,comment,filethumbnailid,type,createdat);
                messageslist.add(message);
            }
            Log.d("demo",messageslist.toString());
            return messageslist;
        }


    }


}
