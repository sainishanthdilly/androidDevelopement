package com.example.nishanth.okhttpclientdemo;

import java.io.Serializable;

/**
 * Created by nishanth on 3/27/2017.
 */
/*
Shireen
Nishanth
Group 04
*/


public class AddMsg implements Serializable {
    String status;
    msgData data;

    public AddMsg() {
    }

    @Override
    public String toString() {
        return "AddMsg{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public AddMsg(String status, msgData data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public msgData getData() {
        return data;
    }

    public void setData(msgData data) {
        this.data = data;
    }
}
