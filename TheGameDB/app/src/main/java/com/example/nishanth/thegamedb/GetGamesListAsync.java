package com.example.nishanth.thegamedb;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by nishanth on 2/17/2017.
 */

public class GetGamesListAsync extends AsyncTask<RequestParams,Void,ArrayList<GamesList>>  {
    private MainActivity activity;

    public GetGamesListAsync(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(ArrayList<GamesList> gamesLists) {
        super.onPostExecute(gamesLists);
        activity.setUpGamesData(gamesLists);


    }

    @Override
    protected ArrayList<GamesList> doInBackground(RequestParams... params) {
        RequestParams pr = params[0];HttpURLConnection con;
        try {
             con = pr.setUpConnection();
             return GamesListUtil.GamesListPullParser.parseGames(con.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static interface SetupGames{
        public void setUpGamesData(ArrayList<GamesList> games_list);
    }
}
