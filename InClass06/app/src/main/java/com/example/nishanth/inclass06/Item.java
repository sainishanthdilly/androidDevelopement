package com.example.nishanth.inclass06;


/**
 * Created by nishanth on 2/13/2017.
 * Group 05
 * Sai Nishanth Dilly
 */

public class Item {

    String title,pubDate,Description;
    String imgUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", Description='" + Description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
