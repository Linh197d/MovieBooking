package com.base.moviebooking.entity;

import java.io.Serializable;

public class Movie implements Serializable {
    private String name;
    private String description;
    private String director;
    private int time;
    private String timeRelease;
    private String image;
    private int id;
    private int ageLimit;

    public Movie() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTimeRelease() {
        return timeRelease;
    }

    public void setTimeRelease(String timeRelease) {
        this.timeRelease = timeRelease;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Movie(String name, String description, String director, int time, String image, int id, int ageLimit) {
        this.name = name;
        this.description = description;
        this.director = director;
        this.time = time;
        this.image = image;
        this.id = id;
        this.ageLimit = ageLimit;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
