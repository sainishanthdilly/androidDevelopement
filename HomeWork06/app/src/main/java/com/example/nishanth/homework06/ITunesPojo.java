package com.example.nishanth.homework06;

/**
 * Created by nishanth on 2/24/2017.
 */

public class ITunesPojo {
    String label;
    String price;
    String imgURL;
    boolean isCheckedFav;

    public ITunesPojo() {

        isCheckedFav = false;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
