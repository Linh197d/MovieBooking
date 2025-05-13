package com.base.moviebooking.entity;

import java.io.Serializable;
import java.util.List;

public class MovieSchedule implements Serializable {
    private String name;
    private String description;
    private String director;
    private String imageDirector;
    private int time;
    private String timeRelease;
    private String image;
    private int id;
    private int ageLimit;
    private String trailer;
    private String format;
    private float rate;
    private List<Schedule> listSchedule;

    public MovieSchedule() {
    }

    public MovieSchedule(String name, String description, String director, String imageDirector, int time, String timeRelease, String image, int id, int ageLimit, String trailer, float rate, String format, List<Schedule> listSchedule) {
        this.name = name;
        this.description = description;
        this.director = director;
        this.imageDirector = imageDirector;
        this.time = time;
        this.timeRelease = timeRelease;
        this.image = image;
        this.id = id;
        this.ageLimit = ageLimit;
        this.trailer = trailer;
        this.rate = rate;
        this.format = format;
        this.listSchedule = listSchedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImageDirector() {
        return imageDirector;
    }

    public void setImageDirector(String imageDirector) {
        this.imageDirector = imageDirector;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<Schedule> getListSchedule() {
        return listSchedule;
    }

    public void setListSchedule(List<Schedule> listSchedule) {
        this.listSchedule = listSchedule;
    }
}
