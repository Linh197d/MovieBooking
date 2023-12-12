package com.base.moviebooking.entity;

public class Account {
    private int id;
    private String fullName;
    private String address;
    private String dateOfBirth;
    private String email;
    private String password;
    private int gender;
    private String avatar;

    public Account(int id, String fullName, String address, String email, String password, int gender, String avatar,String dateOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.avatar = avatar;
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
