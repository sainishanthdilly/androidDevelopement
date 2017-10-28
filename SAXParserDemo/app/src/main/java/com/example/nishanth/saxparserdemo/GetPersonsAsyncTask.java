package com.example.nishanth.saxparserdemo;

import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by nishanth on 2/13/2017.
 */

public class GetPersonsAsyncTask extends AsyncTask<String,Void,ArrayList<Person>> {


    @Override
    protected ArrayList<Person> doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int status = con.getResponseCode();
            if(status == HttpURLConnection.HTTP_OK)
            {
                InputStream is = con.getInputStream();
                try {
                   return PersonsUtil.PersonPullParser.parsePersons(is);
                }  catch (XmlPullParserException e) {
                    e.printStackTrace();
                }

            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(ArrayList<Person> persons) {
        super.onPostExecute(persons);
        Log.d("Demo",persons.toString());
    }

}
