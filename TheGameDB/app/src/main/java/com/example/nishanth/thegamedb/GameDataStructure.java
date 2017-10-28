package com.example.nishanth.thegamedb;

import android.graphics.Bitmap;

/**
 * Created by nishanth on 2/20/2017.
 */

public class GameDataStructure {

    Bitmap logo;
    String Details;

    public GameDataStructure(Bitmap logo, String details) {
        this.logo = logo;
        Details = details;
    }

    @Override
    public String toString() {
        return "GameDataStructure{" +
                " Details='" + Details + '\'' +
                '}';
    }
}
