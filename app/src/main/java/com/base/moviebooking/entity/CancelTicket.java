package com.base.moviebooking.entity;

public class CancelTicket {
    private String code;

    public CancelTicket(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
