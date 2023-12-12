package com.base.moviebooking.entity;

public class LoginResponse {
    private boolean success;
    private Account data;
    private String accessToken;

    public LoginResponse(boolean success, Account data,String accessToken) {
        this.success = success;
        this.data = data;
        this.accessToken=accessToken;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Account getData() {
        return data;
    }

    public void setData(Account data) {
        this.data = data;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
