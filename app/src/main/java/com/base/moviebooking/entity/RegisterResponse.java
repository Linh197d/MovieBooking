package com.base.moviebooking.entity;

public class RegisterResponse {
    private boolean success;
    private Data data;

    public RegisterResponse(boolean success, Data data) {
        this.success = success;
        this.data = data;
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
}
