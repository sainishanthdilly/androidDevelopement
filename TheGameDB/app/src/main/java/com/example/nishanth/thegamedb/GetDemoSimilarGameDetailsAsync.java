package com.example.nishanth.thegamedb;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by nishanth on 2/20/2017.
 */

public class GetDemoSimilarGameDetailsAsync extends AsyncTask<RequestParams,Void,Game> {


        GameSimilarDetailsDemo activity;

        public GetDemoSimilarGameDetailsAsync(GameSimilarDetailsDemo activity) {
            this.activity = activity;


        }

        @Override
        protected void onPostExecute(Game game) {
            super.onPostExecute(game);

                activity.setGame(game);


        }

        @Override
        protected Game doInBackground(RequestParams... params) {

            RequestParams pr = params[0];
            HttpURLConnection con;
            try {
                con = pr.setUpConnection();
                return GamesListUtil.GamePullParser.parseGame(con.getInputStream());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }


            return null;
        }

        public static interface SetUpGame{
            public void setGame(Game g);
        }

    }


