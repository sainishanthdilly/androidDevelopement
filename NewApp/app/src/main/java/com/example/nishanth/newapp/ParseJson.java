package com.example.nishanth.newapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nishanth on 2/6/2017.
 */

public class ParseJson {

    ArrayList<NewsData> parseJsonNews(String s)
    {

        ArrayList<NewsData> al = new ArrayList<NewsData>();

        try {
            JSONObject root = new JSONObject(s);


        JSONArray articles = root.getJSONArray("articles");

          for(int i=0; i< articles.length() ; i++){

              JSONObject ob = articles.getJSONObject(i);
              String a =(String) ob.getString("author");
              String t = (String)        ob.getString("title");
             String d =(String) ob.getString("description");
            String u = (String)      ob.getString("url");
            String il= (String)       ob.getString("urlToImage");
              String p =(String)      ob.getString( "publishedAt");

              al.add(new NewsData(a,t,d,u,il,p));



          }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return al;


    }
}
