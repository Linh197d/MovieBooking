package com.base.mvvmbaseproject.entity;

import com.google.gson.annotations.SerializedName;

public class SearchResponse {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("icon")
    private String icon;
    @SerializedName("image_web")
    private String image_web;
    @SerializedName("description")
    private String description;

    public SearchResponse(int id, String name, String icon, String image_web, String description) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.image_web = image_web;
        this.description = description;
    }

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImage_web() {
        return image_web;
    }

    public void setImage_web(String image_web) {
        this.image_web = image_web;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
