package com.base.moviebooking.entity;

public class UserUpdate {
    private String fullName;
    private String address;
    private String dateOfBirth;
    private int gender;
    private String avatar;

    public UserUpdate(String fullName, String address, String dateOfBirth, int gender, String avatar) {
        this.fullName = fullName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
