package com.example.nishanth.midtermexam;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by nishanth on 3/20/2017.
 */

public class GetShopAsync extends AsyncTask<RequestParams,Void,ArrayList<Movie>> {

    MainActivity activity;
    GetShopAsync(MainActivity activity){
        this.activity = activity;
    }


    static interface GetData {
        public void setData(ArrayList<Movie> movieArrayList);
    }



    @Override
    protected ArrayList<Movie> doInBackground(RequestParams... params) {

        RequestParams rq = params[0];

        try {
            HttpURLConnection con = rq.setUpConnection();
            InputStream is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String s;
            while((s = br.readLine())!=null){
                sb.append(s);
            }


            return ParseShoppingDataJson.parseJsonQuestions(sb.toString());

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;


    }


    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        activity.setData(movies);

        super.onPostExecute(movies);
    }
}
