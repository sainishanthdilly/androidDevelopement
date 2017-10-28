package com.example.nishanth.okhttpclientdemo;


/**
 * Created by nishanth on 3/27/2017.
 */
/*
Shireen
Nishanth
Group 04
*/


public class Message {
String UserFname,UserLname,Id,Comment,FileThumbnailId,Type,CreatedAt;

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "UserFname='" + UserFname + '\'' +
                ", UserLname='" + UserLname + '\'' +
                ", Id='" + Id + '\'' +
                ", Comment='" + Comment + '\'' +
                ", FileThumbnailId='" + FileThumbnailId + '\'' +
                ", Type='" + Type + '\'' +
                ", CreatedAt='" + CreatedAt + '\'' +
                '}';
    }

    public Message(String userFname, String userLname, String id, String comment, String fileThumbnailId, String type, String createdAt) {
        UserFname = userFname;
        UserLname = userLname;
        Id = id;
        Comment = comment;
        FileThumbnailId = fileThumbnailId;
        Type = type;
        CreatedAt = createdAt;
    }

    public String getUserFname() {
        return UserFname;
    }

    public void setUserFname(String userFname) {
        UserFname = userFname;
    }

    public String getUserLname() {
        return UserLname;
    }

    public void setUserLname(String userLname) {
        UserLname = userLname;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getFileThumbnailId() {
        return FileThumbnailId;
    }

    public void setFileThumbnailId(String fileThumbnailId) {
        FileThumbnailId = fileThumbnailId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }
}
