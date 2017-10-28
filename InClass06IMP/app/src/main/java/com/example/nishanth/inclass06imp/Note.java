package com.example.nishanth.inclass06imp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by nishanth on 2/26/2017.
 */

public class Note extends RealmObject {

    @PrimaryKey
    private int _id;
    private String note,priority;
    private String time,status;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
