package com.example.nishanth.homework06;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nishanth on 2/24/2017.
 *
 Sai Nishanth Dilly
 Shireen Shaik
 Group 04

 */

public class JsonUtilGetITunesData {

    public  static class ITunesDataJson{

        public  static ArrayList<ITunesPojo> setJson(String s){

            ArrayList<ITunesPojo> tunesPojoArrayList = new ArrayList<>();
            try {
                JSONObject root = new JSONObject(s);
                JSONObject fee = root.getJSONObject("feed");
                JSONArray array = fee.getJSONArray("entry");
                for(int i=0; i < array.length(); i++){
                    ITunesPojo iTunesPojo = new ITunesPojo();

                    JSONObject job= (JSONObject) array.getJSONObject(i);
                    iTunesPojo.setLabel(job.getJSONObject("im:name").getString("label"));
                    iTunesPojo.setImgURL(job.getJSONArray("im:image").getJSONObject(0).getString("label"));
                    iTunesPojo.setPrice(job.getJSONObject("im:price").getString("label").replace("$","USD "));
                    tunesPojoArrayList.add(iTunesPojo);

                }



            } catch (JSONException e) {
                e.printStackTrace();
            }


            return tunesPojoArrayList;
        }

    }

}
