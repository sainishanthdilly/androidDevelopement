package com.example.nishanth.triviaquiz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by nishanth on 2/10/2017.
 *
 Sai Nishanth Dilly
 Shireen Shaik
 Group 04


*/

public class Questions implements Serializable {
String text;
    String[] choices;
    String answer;
        String image;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Questions(JSONObject question) throws JSONException {

       text = question.getString("text");
        if(question.has("image")) {
            image = question.getString("image");
        }
        else
        image = "";
        JSONObject ch = question.getJSONObject("choices");
        answer = ch.getString("answer");
        JSONArray jr = ch.getJSONArray("choice");
        choices = new String[jr.length()];
        for(int i=0; i < jr.length(); i++){
            choices[i] = jr.getString(i);

        }





    }



}
