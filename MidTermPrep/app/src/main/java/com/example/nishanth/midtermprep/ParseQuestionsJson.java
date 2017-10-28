package com.example.nishanth.midtermprep;

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

    public  static ArrayList<Questions> parseJsonQuestions(String s)  {
        ArrayList<Questions> questionsArray  = null;

        try {
            JSONObject root = new JSONObject(s);
            JSONArray arr = root.getJSONArray("questions");
            questionsArray = new ArrayList<Questions>();

            for(int i=0;i<arr.length();i++){
                JSONObject question = arr.getJSONObject(i);
                Questions q = new Questions(question);
                questionsArray.add(q);





            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


        return questionsArray;

    }

}
