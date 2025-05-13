package com.base.moviebooking.entity;

import java.io.Serializable;

public class FilmInfo implements Serializable {
    private String tenDienAnh;
    private int urlImage;
    private int baiviet1;
    private int baiviet2;

    public FilmInfo(String tenDienAnh, int urlImage, int baiviet, int baiviet2) {
        this.tenDienAnh = tenDienAnh;
        this.urlImage = urlImage;
        this.baiviet1 = baiviet;
        this.baiviet2 = baiviet2;
    }

    public String getTenDienAnh() {
        return tenDienAnh;
    }

    public void setTenDienAnh(String tenDienAnh) {
        this.tenDienAnh = tenDienAnh;
    }

    public int getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(int urlImage) {
        this.urlImage = urlImage;
    }

    public int getBaiviet() {
        return baiviet1;
    }

    public void setBaiviet(int baiviet) {
        this.baiviet1 = baiviet;
    }

    public int getBaiviet2() {
        return baiviet2;
    }

    public void setBaiviet2(int baiviet2) {
        this.baiviet2 = baiviet2;
    }
}
