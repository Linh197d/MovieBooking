package com.base.mvvmbaseproject.entity;

import java.util.Date;

public class UpdateDataUser {
    private  String name;
    private String birthday;
   private String address;
   private int gender;
   private String phone;

    public UpdateDataUser(String name, String birthday, String address, int gender,  String phone) {
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.gender = gender;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
