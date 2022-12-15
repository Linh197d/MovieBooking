package com.base.mvvmbaseproject.entity;

public class Content {
    private double amount;
    private int code;
    private String name;
    private String status;

    public double getAmount() {
        return amount;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Content(double amount, int code, String name, String status) {
        this.amount = amount;
        this.code = code;
        this.name = name;
        this.status = status;
    }
}
