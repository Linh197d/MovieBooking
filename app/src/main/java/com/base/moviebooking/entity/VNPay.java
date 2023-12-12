package com.base.moviebooking.entity;

public class VNPay {
    private Integer amount;
    private String language;

    public VNPay(Integer amount, String language) {
        this.amount = amount;
        this.language = language;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
