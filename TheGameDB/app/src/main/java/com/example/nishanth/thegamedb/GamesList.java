package com.example.nishanth.thegamedb;

import java.text.SimpleDateFormat;

/**
 * Created by nishanth on 2/17/2017.
 */

public class GamesList {


    int id;
    String gameTitle;
    String releaseDate;
    String platform;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
