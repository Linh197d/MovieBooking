package com.base.moviebooking.entity;

public class Amount {
    private int amount;
    private int amount_vip;

    public Amount(int amount, int amount_vip) {
        this.amount = amount;
        this.amount_vip = amount_vip;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount_vip() {
        return amount_vip;
    }

    public void setAmount_vip(int amount_vip) {
        this.amount_vip = amount_vip;
    }
}
