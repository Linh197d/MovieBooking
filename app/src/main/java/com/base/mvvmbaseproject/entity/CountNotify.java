package com.base.mvvmbaseproject.entity;

public class CountNotify {
    private int count_notification;
    private String format_date;

    public CountNotify(int count_notification, String format_date) {
        this.count_notification = count_notification;
        this.format_date = format_date;
    }

    public int getCount_notification() {
        return count_notification;
    }

    public String getFormat_date() {
        return format_date;
    }
}
