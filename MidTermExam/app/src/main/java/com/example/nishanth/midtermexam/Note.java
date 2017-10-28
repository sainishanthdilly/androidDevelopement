package com.example.nishanth.midtermexam;

/**
 * Created by nishanth on 2/26/2017.
 */

public class Note {
    long _id;
    String note,priority,
    time,status;

    public Note(String note, String priority, String time, String status) {
        this.note = note;
        this.priority = priority;
        this.time = time;
        this.status = status;
    }

    Note(){

    }

    @Override
    public String toString() {
        return "Note{" +
                "_id=" + _id +
                ", note='" + note + '\'' +
                ", priority='" + priority + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
