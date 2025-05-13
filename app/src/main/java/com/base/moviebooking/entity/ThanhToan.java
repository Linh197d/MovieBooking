package com.base.moviebooking.entity;

import java.util.ArrayList;

public class ThanhToan {
    private String scheduleId;
    private ArrayList<Integer> selectedChairs;
    private int totalMoney;
    private ArrayList<ProductThanhToan> products;

    public ThanhToan(String scheduleId, ArrayList<Integer> selectedChairs, int totalMoney, ArrayList<ProductThanhToan> products) {
        this.scheduleId = scheduleId;
        this.selectedChairs = selectedChairs;
        this.totalMoney = totalMoney;
        this.products = products;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public ArrayList<ProductThanhToan> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductThanhToan> products) {
        this.products = products;
    }

    public ArrayList<Integer> getSelectedChairs() {
        return selectedChairs;
    }

    public void setSelectedChairs(ArrayList<Integer> selectedChairs) {
        this.selectedChairs = selectedChairs;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }
}
