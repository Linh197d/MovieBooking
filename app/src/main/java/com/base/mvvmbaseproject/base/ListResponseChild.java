package com.base.mvvmbaseproject.base;

import com.base.mvvmbaseproject.entity.LSKhamBenh;

import java.util.List;

public class ListResponseChild extends ListResponse<LSKhamBenh>{
    private int current_page,total_page, per_page, total;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ListResponseChild(int current_page, int total_page, int per_page, int total) {
        this.current_page = current_page;
        this.total_page = total_page;
        this.per_page = per_page;
        this.total = total;
    }

    public ListResponseChild(int type, List data, Throwable error, int current_page, int total_page, int per_page, int total) {
        super(type, data, error);
        this.current_page = current_page;
        this.total_page = total_page;
        this.per_page = per_page;
        this.total = total;
    }
}
