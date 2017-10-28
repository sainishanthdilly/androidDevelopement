package com.example.nishanth.newapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by nishanth on 2/6/2017.
 */
class GetJsonRes extends AsyncTask<RequestParam,Void,String> {


    SendJsonData data;
       GetJsonRes(MainActivity d){
        data = d;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        if(s!=null) {
            super.onPostExecute(s);
            data.sendJData(s);

        }
        //super.onPostExecute(s);
    }


   /* @Override
    protected void onPostExecute(String s) {

        //Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
    }*/

    @Override
    protected String doInBackground(RequestParam... params) {

        RequestParam p = params[0];
        HttpURLConnection con ;
        BufferedReader br = null;
        InputStream in = null;


        try {
            con = p.setUpConnection();
            in = con.getInputStream();
            br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb =  new StringBuilder();
            String s;
            while((s = br.readLine())!=null  ){
                sb.append(s+"\n");
            }

            return sb.toString();









        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
        finally {
            try {
                if(br!=null)
                br.close();
                if(in!=null)
                in.close();

            } catch (IOException e) {
                e.printStackTrace();
            }



        }



    }

    static public interface SendJsonData{
        public void sendJData(String s);
        public Context getContext();

    }


}
