package com.example.nishanth.midtermlastyear;

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

    public  static ArrayList<Movie> parseJsonQuestions(String s)  {
        ArrayList<Movie> moviesArray  = null;

        try {
            JSONObject root1 = new JSONObject(s);
            JSONObject root = root1.getJSONObject("feed");

            JSONArray arr = root.getJSONArray("entry");
            moviesArray = new ArrayList<Movie>();

            for(int i=0;i<arr.length();i++){
                JSONObject question = arr.getJSONObject(i);
                Movie q = new Movie();
                q.setName(question.getJSONObject("im:name").getString("label"));
                q.setImgUrl(question.getJSONArray("im:image").getJSONObject(0).getString("label"));
                q.setPrice(question.getJSONObject("im:price").getString("label"));






                moviesArray.add(q);


            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


        return moviesArray;

    }

}
