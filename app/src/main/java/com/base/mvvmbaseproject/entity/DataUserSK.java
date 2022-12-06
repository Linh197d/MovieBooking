package com.base.mvvmbaseproject.entity;

public class DataUserSK {
    private String name;
    private int gender;
    private int users_weight;
    private int users_height;
    private String users_blood_group;
    private String users_judgment;
    private String user_age;
    private String avatar;

    public DataUserSK(String name, int gender, int users_weight, int users_height, String users_blood_group, String users_judgment, String user_age, String avatar) {
        this.name = name;
        this.gender = gender;
        this.users_weight = users_weight;
        this.users_height = users_height;
        this.users_blood_group = users_blood_group;
        this.users_judgment = users_judgment;
        this.user_age = user_age;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public int getUsers_weight() {
        return users_weight;
    }

    public int getUsers_height() {
        return users_height;
    }

    public String getUsers_blood_group() {
        return users_blood_group;
    }

    public String getUsers_judgment() {
        return users_judgment;
    }

    public String getUser_age() {
        return user_age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setUsers_weight(int users_weight) {
        this.users_weight = users_weight;
    }

    public void setUsers_height(int users_height) {
        this.users_height = users_height;
    }

    public void setUsers_blood_group(String users_blood_group) {
        this.users_blood_group = users_blood_group;
    }

    public void setUsers_judgment(String users_judgment) {
        this.users_judgment = users_judgment;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
