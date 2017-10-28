package com.example.nishanth.thegamedb;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by nishanth on 2/17/2017.
 */

public class Game {

    Bitmap img;
    String game_name;
    String genre;
    String publisher;
    String youtubeVideo;
    int similar_count;

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    String overView;
    ArrayList<Integer> similar_id;

    public ArrayList<Integer> getSimilar_id() {
        return similar_id;
    }

    public void setSimilar_id(ArrayList<Integer> similar_id)
    {
        this.similar_id = similar_id;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getYoutubeVideo() {
        return youtubeVideo;
    }

    public void setYoutubeVideo(String youtubeVideo) {
        this.youtubeVideo = youtubeVideo;
    }

    public int get () {
        return similar_count;
    }

    public void setSimilar_count(int similar_count) {
        this.similar_count = similar_count;
    }
}
