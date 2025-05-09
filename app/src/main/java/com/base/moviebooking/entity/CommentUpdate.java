package com.base.moviebooking.entity;

public class CommentUpdate {
    private int id;
    private int movie_id;
    private int rate;
    private String content;

    public CommentUpdate() {
    }

    public CommentUpdate(int id, int movie_id, int rate, String content) {
        this.id = id;
        this.movie_id = movie_id;
        this.rate = rate;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
