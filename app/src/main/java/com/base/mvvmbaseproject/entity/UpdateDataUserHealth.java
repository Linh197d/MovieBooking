package com.base.mvvmbaseproject.entity;

public class UpdateDataUserHealth {
    private float weight;
    private int height;
    private String blood_group;
    private String   judgment;

    public UpdateDataUserHealth(float weight, int height, String blood_group, String judgment) {
        this.weight = weight;
        this.height = height;
        this.blood_group = blood_group;
        this.judgment = judgment;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getJudgment() {
        return judgment;
    }

    public void setJudgment(String judgment) {
        this.judgment = judgment;
    }
}
