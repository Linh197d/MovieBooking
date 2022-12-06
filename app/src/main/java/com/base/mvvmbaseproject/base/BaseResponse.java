package com.base.mvvmbaseproject.base;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("code")
    private int status;
    //private int msg;
    private String msg;
    public int getStatus() {
        return status;
    }

    /*public int getMsg() {
        return msg;
    }*/

    public String getMsg() {
        return msg;
    }
}
