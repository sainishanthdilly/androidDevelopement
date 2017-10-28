package com.example.nishanth.homework09;

import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by nishanth on 4/20/2017.
 */

public class User_Table {

    String user_id;
    String email;
    String first_name;
    String last_name;
    String profile_pic;
    HashMap<String,Integer> friends;
    HashMap<String,Integer> sentRequests;
    HashMap<String,Integer> acceptRequests;



    public User_Table() {
    }

    public User_Table(String user_id, String email, String first_name, String last_name, String profile_pic, HashMap<String, Integer> friends, HashMap<String, Integer> sentRequests, HashMap<String, Integer> acceptRequests) {
        this.user_id = user_id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.profile_pic = profile_pic;
        this.friends = friends;
        this.sentRequests = sentRequests;
        this.acceptRequests = acceptRequests;
    }



    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public HashMap<String, Integer> getFriends() {
        return friends;
    }

    public void setFriends(HashMap<String, Integer> friends) {
        this.friends = friends;
    }

    public HashMap<String, Integer> getSentRequests() {
        return sentRequests;
    }

    public void setSentRequests(HashMap<String, Integer> sentRequests) {
        this.sentRequests = sentRequests;
    }

    public HashMap<String, Integer> getAcceptRequests() {
        return acceptRequests;
    }

    public void setAcceptRequests(HashMap<String, Integer> acceptRequests) {
        this.acceptRequests = acceptRequests;
    }


    @Override
    public String toString() {
        return "User_Table{" +
                "user_id='" + user_id + '\'' +
                ", email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", profile_pic='" + profile_pic + '\'' +
                ", friends=" + friends +
                ", sentRequests=" + sentRequests +
                ", acceptRequests=" + acceptRequests +
                '}';
    }
}
