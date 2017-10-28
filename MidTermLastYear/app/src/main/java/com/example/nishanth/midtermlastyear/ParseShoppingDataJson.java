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

public class ParseShoppingDataJson {

    public  static ArrayList<Movie> parseJsonQuestions(String s)  {
        ArrayList<Movie> movieArrayList  = null;
        movieArrayList  = new ArrayList<>();

        try {

            JSONArray arr = new JSONArray(s);

            for(int i=0;i<arr.length();i++){
                JSONObject obj = arr.getJSONObject(i);
                Movie q = new Movie();
                q.setName(obj.getString("name"));

                JSONObject imgObj =obj.getJSONObject("image_urls");
                JSONArray array = imgObj.getJSONArray("300x400");
                q.setImgUrl(array.getJSONObject(0).getString("url"));


               array = obj.getJSONArray("skus");
                String pr = array.getJSONObject(0).getString("sale_price");
                String mpr =  array.getJSONObject(0).getString("msrp_price");
                q.setPrice(pr);
                q.setDiscount( (Double.toString(  ((Double.parseDouble(mpr) -Double.parseDouble(pr) )*100)/ Double.parseDouble(mpr) )).substring(0,4) + "%");

                movieArrayList.add(q);


            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


        return movieArrayList;

    }

}
