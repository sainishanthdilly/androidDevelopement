package com.example.nishanth.thegamedb;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by nishanth on 2/19/2017.
 */

public class GetSimilarGameAsync extends AsyncTask<RequestParams,Void,GameSimilar> {
    private SimilarGames activity;

    public GetSimilarGameAsync (SimilarGames activity) {
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(GameSimilar gameSimilar) {
        super.onPostExecute(gameSimilar);
        activity.setUpGameData(gameSimilar);


    }

    @Override
    protected GameSimilar doInBackground(RequestParams... params) {
        RequestParams pr = params[0];HttpURLConnection con;
        try {
            con = pr.setUpConnection();
            return GamesListUtil.SimilarGamePullParser.parseGames(con.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static interface SetupSMGame{
        public void setUpGameData(GameSimilar game);
    }
}
