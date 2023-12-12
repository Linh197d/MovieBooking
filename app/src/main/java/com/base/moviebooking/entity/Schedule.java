package com.base.moviebooking.entity;

import java.io.Serializable;

public class Schedule implements Serializable {
    private int id;
    private int room_id;
    private int movie_id;
    private String premiere;
    private String room_name;

    public Schedule(int id, int room_id, int movie_id, String premiere, String room_name) {
        this.id = id;
        this.room_id = room_id;
        this.movie_id = movie_id;
        this.premiere = premiere;
        this.room_name = room_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getPremiere() {
        return premiere;
    }

    public void setPremiere(String premiere) {
        this.premiere = premiere;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }
}
