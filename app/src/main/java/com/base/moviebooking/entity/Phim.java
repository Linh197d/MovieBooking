package com.base.moviebooking.entity;

public class Phim {
    private String tenphim;
    private int urlImage;
    private String ngay;
    private String mota;

    public Phim(String tenphim, int urlImage, String ngay, String mota) {
        this.tenphim = tenphim;
        this.urlImage = urlImage;
        this.ngay = ngay;
        this.mota = mota;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public int getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(int urlImage) {
        this.urlImage = urlImage;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
