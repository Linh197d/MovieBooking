package com.base.moviebooking.entity;

import java.io.Serializable;

public class Theater  implements Serializable {

    private int id;
    private String name;
    private String urlImage;
    private String address;
    private String city;
    private String phone;

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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Theater(int id, String name, String urlImage, String address, String city, String phone) {
        this.id = id;
        this.name = name;
        this.urlImage = urlImage;
        this.address = address;
        this.city = city;
        this.phone = phone;
    }
}
