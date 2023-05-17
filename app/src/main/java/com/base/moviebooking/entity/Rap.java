package com.base.moviebooking.entity;

public class Rap {
    private String tenRap;
    private int urlImage;
    private String diachi;
    private String phone;

    public String getTenRap() {
        return tenRap;
    }

    public void setTenRap(String tenRap) {
        this.tenRap = tenRap;
    }

    public int getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(int urlImage) {
        this.urlImage = urlImage;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Rap(String tenRap, int urlImage, String diachi, String phone) {
        this.tenRap = tenRap;
        this.urlImage = urlImage;
        this.diachi = diachi;
        this.phone = phone;
    }
}
