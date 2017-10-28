package com.example.nishanth.newapp;

/**
 * Created by nishanth on 2/6/2017.
 */

public class NewsData {

    String author;
    String title;


    String descr;
    String url;
    String urlToImage;
    String publish;


    public String getUrlToImage() {
        return urlToImage;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublish() {
        return publish;
    }

    public String getDescr() {
        return descr;
    }

    NewsData(String author, String title, String descr, String url,String urlToImage,String publish){
        this.title = title;
        this.author = author;
        this.publish = publish;
        this.url = url;
        this.urlToImage = urlToImage;
        this.descr = descr;




    }





}
