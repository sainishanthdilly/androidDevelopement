package com.example.nishanth.listviewdemo;

/**
 * Created by nishanth on 2/20/2017.
 */

public class ColorAc {

    String color;
    String hexCode;

    public ColorAc(String color, String hexCode) {
        this.color = color;
        this.hexCode = hexCode;
    }

    @Override
    public String toString() {
        return "ColorAc{" +
                "color='" + color + '\'' +
                '}';
    }
}
