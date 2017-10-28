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

public class msgData implements Serializable {
    String Id,Comment,UserId,FileThumbnailId,Type,CreatedAt,UpdatedAt;

    public msgData() {
    }

    @Override
    public String toString() {
        return "msgData{" +
                "Id='" + Id + '\'' +
                ", Comment='" + Comment + '\'' +
                ", UserId='" + UserId + '\'' +
                ", FileThumbnailId='" + FileThumbnailId + '\'' +
                ", Type='" + Type + '\'' +
                ", CreatedAt='" + CreatedAt + '\'' +
                ", UpdatedAt='" + UpdatedAt + '\'' +
                '}';
    }

    public msgData(String id, String comment, String userId, String type, String createdAt, String updatedAt) {
        Id = id;
        Comment = comment;
        UserId = userId;
        Type = type;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
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

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
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

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }
}
