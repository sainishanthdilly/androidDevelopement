package com.example.nishanth.homework09;

import java.util.ArrayList;


public class ChatroomLocal {





    String roomid,roomname,created_by,created_byid,created_time;
    ArrayList<Message_pojo> message_pojoArrayList=new ArrayList<Message_pojo>();
    String trip_icon;

    public String getCreated_time() {
        return created_time;
    }

    public String getTrip_icon() {
        return trip_icon;
    }

    public void setTrip_icon(String trip_icon) {
        this.trip_icon = trip_icon;
    }

    public ChatroomLocal(String roomid, String roomname, String created_by, String created_byid, String created_time, ArrayList<Message_pojo> message_pojoArrayList, String trip_icon) {
        this.roomid = roomid;
        this.roomname = roomname;
        this.created_by = created_by;
        this.created_byid = created_byid;
        this.created_time = created_time;
        this.message_pojoArrayList = message_pojoArrayList;
        this.trip_icon = trip_icon;
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "roomid='" + roomid + '\'' +

                ", roomname='" + roomname + '\'' +
                ", created_by='" + created_by + '\'' +
                ", created_byid='" + created_byid + '\'' +
                ", created_time='" + created_time + '\'' +
                ", message_pojoArrayList=" + message_pojoArrayList +
                ", trip_icon='" + trip_icon + '\'' +

                '}';
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }




    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getCreated_byid() {
        return created_byid;
    }

    public void setCreated_byid(String created_byid) {
        this.created_byid = created_byid;
    }

    public ArrayList<Message_pojo> getMessage_pojoArrayList() {
        return message_pojoArrayList;
    }

    public void setMessage_pojoArrayList(ArrayList<Message_pojo> message_pojoArrayList) {
        this.message_pojoArrayList = message_pojoArrayList;
    }

    public ChatroomLocal() {
    }

}
