package com.example.nishanth.beforeinclass4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isConnectedOnline())
        {
            new GetImage().execute("https://9to5google.files.wordpress.com/2017/01/pixel_xl_systemupdate_1.jpg?quality=82&strip=all&w=1000&strip=all&w=1500&h=0");
        }
    }

    private boolean isConnectedOnline(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo in =cm.getActiveNetworkInfo();
        if(in!=null &&in.isConnected())
            return true;




        return false;
    }


    private class GetImage extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageBitmap(bitmap);

        }

        @Override
        protected Bitmap doInBackground(String... params) {

            try {
                URL u = new URL(params[0]);
               HttpURLConnection con = (HttpURLConnection) u.openConnection();
                 Bitmap bm = BitmapFactory.decodeStream(con.getInputStream());

                return  bm;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }

}


