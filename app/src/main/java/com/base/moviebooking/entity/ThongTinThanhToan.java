package com.base.moviebooking.entity;

import java.util.ArrayList;

public class ThongTinThanhToan {
    private int id;
    private int value;
    private String code;
    private String cinema;
    private String room;
    private String movie;
    private ArrayList<String> chairs;
    private ArrayList<ProductThanhToan> products;
    private String premiere;
    private int is_cancel;

    public ThongTinThanhToan(int id, int value, String code, String cinema, String room) {
        this.id = id;
        this.value = value;
        this.code = code;
        this.cinema = cinema;
        this.room = room;
    }

    public int getIs_cancel() {
        return is_cancel;
    }

    public void setIs_cancel(int is_cancel) {
        this.is_cancel = is_cancel;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public ArrayList<String> getChairs() {
        return chairs;
    }

    public void setChairs(ArrayList<String> chairs) {
        this.chairs = chairs;
    }

    public ArrayList<ProductThanhToan> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductThanhToan> products) {
        this.products = products;
    }

    public String getPremiere() {
        return premiere;
    }

    public void setPremiere(String premiere) {
        this.premiere = premiere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
