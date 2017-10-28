package com.example.nishanth.triviaquiz;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by nishanth on 2/10/2017.
 *
 Sai Nishanth Dilly
 Shireen Shaik
 Group 04


*/

public class GetQuestionsAsync extends AsyncTask<RequestParam,Void,ArrayList<Questions>> {
 MainActivity activity;

    GetQuestionsAsync(MainActivity activity){
        this.activity = activity;

    }

    @Override
    protected void onPostExecute(ArrayList<Questions> questionses) {
        super.onPostExecute(questionses);
        activity.setQuestions(questionses);





    }

    @Override
    protected ArrayList<Questions> doInBackground(RequestParam... params) {
        RequestParam pr = params[0];
        HttpURLConnection con;
        InputStream is = null;
        ArrayList<Questions> q = null;

        try {
            con  = pr.setUpConnection();
             is = con.getInputStream();
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String s;
            while ( (s=br.readLine())!=null){
                sb.append(s);

            }
             q= ParseQuestionsJson.parseJsonQuestions(sb.toString());


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



        return  q;
    }

    public static interface SetQuestions{
        public void setQuestions(ArrayList<Questions> questions);
    }

}
