package com.example.nishanth.homework09;

import android.content.Intent;

import java.util.HashMap;

/**
 * Created by Shireen on 20-04-2017.
 */

public class Message_pojo {
    String message_userid;
    String message_username;
    String actual_message;
    String userImage;
    String message_id;
    String image_message,message_time;
    HashMap<String,Integer> deleted;

    public Message_pojo(String message_userid, String message_username, String actual_message, String userImage, String message_id, String image_message, String message_time, HashMap<String, Integer> deleted) {
        this.message_userid = message_userid;
        this.message_username = message_username;
        this.actual_message = actual_message;
        this.userImage = userImage;
        this.message_id = message_id;
        this.image_message = image_message;
        this.message_time = message_time;
        this.deleted = deleted;
    }

    public Message_pojo() {

    }

    public String getMessage_userid() {
        return message_userid;
    }

    public void setMessage_userid(String message_userid) {
        this.message_userid = message_userid;
    }

    public String getMessage_username() {
        return message_username;
    }

    public void setMessage_username(String message_username) {
        this.message_username = message_username;
    }

    public String getActual_message() {
        return actual_message;
    }

    public void setActual_message(String actual_message) {
        this.actual_message = actual_message;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getImage_message() {
        return image_message;
    }

    public void setImage_message(String image_message) {
        this.image_message = image_message;
    }

    public String getMessage_time() {
        return message_time;
    }

    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }

    public HashMap<String, Integer> getDeleted() {
        return deleted;
    }

    public void setDeleted(HashMap<String, Integer> deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Message_pojo{" +
                "message_userid='" + message_userid + '\'' +
                ", message_username='" + message_username + '\'' +
                ", actual_message='" + actual_message + '\'' +
                ", userImage='" + userImage + '\'' +
                ", message_id='" + message_id + '\'' +
                ", image_message='" + image_message + '\'' +
                ", message_time='" + message_time + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}