package com.example.nishanth.midtermprep;

/**
 * Created by nishanth on 3/9/2017.
 */

public class RadioDataPojo {

    private String title;
    private String datePoted;
    private String imageUrl;
    private String audioUrl;
    private String description;
    private String duration;



    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatePoted() {
        return datePoted;
    }

    public void setDatePoted(String datePoted) {
        this.datePoted = datePoted;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    @Override
    public String toString() {
        return "RadioDataPojo{" +
                "title='" + title + '\'' +
                ", datePoted='" + datePoted + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", audioUrl='" + audioUrl + '\'' +
                '}';
    }
}
