package com.example.nishanth.triviaquiz;

/**
 * Created by nishanth on 2/10/2017.
 *
 Sai Nishanth Dilly
 Shireen Shaik
 Group 04


*/

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class GetImageAsync extends AsyncTask<RequestParam,Void,Bitmap> {
    TriviaActivity activity;
    int curr;

    GetImageAsync(TriviaActivity activity, int curr){
        this.activity = activity;
        this.curr = curr;

  }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        activity.setimage(bitmap,curr);

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
        public void setimage(Bitmap bm,int curr);
    }

}
