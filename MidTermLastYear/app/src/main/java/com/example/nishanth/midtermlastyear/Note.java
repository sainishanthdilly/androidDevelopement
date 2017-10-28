package com.example.nishanth.midtermlastyear;

/**
 * Created by nishanth on 2/26/2017.
 */

public class Note {
    long _id;
    String name,price,thumb_url;

    public Note(String name, String price, String thumb_url) {
        this.name = name;
        this.price = price;
        this.thumb_url = thumb_url;
    }

    public Note() {
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }
}
