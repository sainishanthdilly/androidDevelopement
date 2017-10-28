package com.example.nishanth.okhttpclientdemo;

/**
 * Created by nishanth on 3/27/2017.
 */
/*
Shireen
Nishanth
Group 04
*/

public class Channels {
    String channel_id;
    String channel_name;
    String join;

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    @Override
    public String toString() {
        return "Channels{" +
                "channel_id='" + channel_id + '\'' +
                ", channel_name='" + channel_name + '\'' +
                '}';
    }

    public Channels(String channel_id, String channel_name) {
        this.channel_id = channel_id;
        this.channel_name = channel_name;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }
}
