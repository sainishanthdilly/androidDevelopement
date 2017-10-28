package com.example.nishanth.newapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nishanth on 2/6/2017.
 */

public class GetNewsImage extends AsyncTask<String,Void,Bitmap> {

    GetImg img;


    GetNewsImage(MainActivity img){
       this.img = img;
    }

        @Override
        protected void onPostExecute(Bitmap bitmap) {


            if(bitmap!=null) {
                super.onPostExecute(bitmap);
                img.getImage(bitmap);

            }
           // ImageView iv = (ImageView) findViewById(R.id.imageView);
           // iv.setImageBitmap(bitmap);

        }

        @Override
        protected Bitmap doInBackground(String... params) {
            InputStream in = null;
            Bitmap bm = null;
            try {
                URL u = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) u.openConnection();
                in =con.getInputStream();
                 bm = BitmapFactory.decodeStream(in);




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    in.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            return  bm;

        }


    static public interface GetImg{
        public void getImage(Bitmap bitmap);


    }
    }




