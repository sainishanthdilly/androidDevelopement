package com.example.nishanth.inclass06;

/**
 * Created by nishanth on 2/13/2017.
 * Group 05
 * Sai Nishanth Dilly
*/

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class GetImageAsync extends AsyncTask<RequestParam,Void,Bitmap> {
    MainActivity activity;


    GetImageAsync(MainActivity activity){
        this.activity = activity;


  }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        activity.setimage(bitmap);

    }

    @Override
    protected Bitmap doInBackground(RequestParam... params) {
        RequestParam pr = params[0];
        HttpURLConnection con;
        InputStream is = null;
        Bitmap bm = null;
        try {
            con  = pr.setUpConnection();
            is = con.getInputStream();
             bm = BitmapFactory.decodeStream(is);



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


        return  bm;
    }

    public static interface SetImage{
        public void setimage(Bitmap bm);
    }

}
