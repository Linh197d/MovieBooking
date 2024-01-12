package com.base.moviebooking.entity;

public class LoginResponse {
    private boolean success;
    private Data data;
    private String accessToken;

    public LoginResponse(boolean success, Data data,String accessToken) {
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    public class Data{
        private String message;
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

