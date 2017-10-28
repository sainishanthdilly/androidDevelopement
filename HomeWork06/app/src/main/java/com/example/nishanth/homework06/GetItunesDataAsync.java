package com.example.nishanth.homework06;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by nishanth on 2/24/2017.
 */
/*
Sai Nishanth Dilly
Shireen Shaik
Group 04
 */

public class GetItunesDataAsync extends AsyncTask<RequestParams,Void,ArrayList<ITunesPojo>> {

    SetUpData activity;

    GetItunesDataAsync(MainActivity activity){
        this.activity = activity;
    }


    @Override
    protected void onPostExecute(ArrayList<ITunesPojo> iTunesPojos) {
        super.onPostExecute(iTunesPojos);
        activity.setData(iTunesPojos);
    }

    @Override
    protected ArrayList<ITunesPojo> doInBackground(RequestParams... params) {
        RequestParams req = params[0];
        URL uri;
        HttpURLConnection con;
        InputStream ins=null;
        try {
             con = req.setUpConnection();
            ins = con.getInputStream();
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s;
            while((s= br.readLine())!=null)
            {
                sb.append(s);
            }

            return  JsonUtilGetITunesData.ITunesDataJson.setJson(sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
                e.printStackTrace();
            }
        finally {
            try {
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
