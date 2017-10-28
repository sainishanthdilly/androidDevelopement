package com.example.nishanth.midtermprep;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by nishanth on 3/9/2017.
 */

public class PodCastAsync extends AsyncTask<RequestParams,Void,ArrayList<RadioDataPojo>> {

    private ActivityData activity;

    public PodCastAsync(MainActivity activity) {
        this.activity = activity;

    }

    @Override
    protected void onPostExecute(ArrayList<RadioDataPojo> radioDataPojos) {
        activity.setUpData(radioDataPojos);
        super.onPostExecute(radioDataPojos);
    }

    @Override
    protected ArrayList<RadioDataPojo> doInBackground(RequestParams... params) {

        RequestParams rq = params[0];

        try {
            HttpURLConnection con = rq.setUpConnection();
            InputStream is  =con.getInputStream();

            return XMlParsingRadioPodCastUtil.XMLParsing.radioDataPojoArrayList(is);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return null;
    }
    public static interface ActivityData {
        public void setUpData(ArrayList<RadioDataPojo> radioDataPojoArrayList);
    }


}
