package com.base.moviebooking.entity;

import java.io.Serializable;

public class Comment implements Serializable {
    private int id;
    private int userId;
    private String fullName;
    private String avatar;
    private int movieId;
    private String content;
    private int rate;
    private String createAt;

    public Comment() {
    }

    public Comment(int id, int userId, String fullName, String avatar, int movieId, String content, int rate, String createAt) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.avatar = avatar;
        this.movieId = movieId;
        this.content = content;
        this.rate = rate;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
