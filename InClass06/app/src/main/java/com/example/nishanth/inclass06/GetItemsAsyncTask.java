package com.example.nishanth.inclass06;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;


/**
 * Created by nishanth on 2/13/2017.
 * Group 05
 * Sai Nishanth Dilly
 */

public class GetItemsAsyncTask extends AsyncTask<RequestParam,Void,ArrayList<Item>> {

    MainActivity activity;

    GetItemsAsyncTask(MainActivity activity){
        this.activity = activity;
    }


    @Override
    protected ArrayList<Item> doInBackground(RequestParam... params) {

        RequestParam pr = params[0];
        HttpURLConnection con;
        InputStream is = null;

        try {
            con  = pr.setUpConnection();

            //con.setRequestMethod("GET");
            con.connect();
            int status = con.getResponseCode();
            if(status == HttpURLConnection.HTTP_OK)
            {
                 is = con.getInputStream();
                try {
                   return ItemsUtil.PersonPullParser.parsePersons(is);
                }  catch (XmlPullParserException e) {
                    e.printStackTrace();
                }

            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

        return null;
    }


    @Override
    protected void onPostExecute(ArrayList<Item> items) {
        super.onPostExecute(items);
        Log.d("Demo",items.toString());
        activity.setData(items);

    }

    public static interface SetData{
        public void setData(ArrayList<Item> items);
    }


}
