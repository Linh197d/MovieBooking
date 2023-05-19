package com.base.moviebooking.entity;

public class DienAnh {
    private String tenDienAnh;
    private int urlImage;
    private String baiviet;

    public DienAnh(String tenDienAnh, int urlImage, String baiviet) {
        this.tenDienAnh = tenDienAnh;
        this.urlImage = urlImage;
        this.baiviet = baiviet;
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

    public String getBaiviet() {
        return baiviet;
    }

    public void setBaiviet(String baiviet) {
        this.baiviet = baiviet;
    }
}
