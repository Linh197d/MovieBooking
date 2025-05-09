package com.base.moviebooking.entity;

import java.io.Serializable;

public class CreateComment implements Serializable {
    private int movie_id;
    private String content;
    private int rate;

    public CreateComment() {
    }

    public CreateComment(int movie_id, String content, int rate) {
        this.movie_id = movie_id;
        this.content = content;
        this.rate = rate;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
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
}
