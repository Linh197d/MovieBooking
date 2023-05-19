package com.base.moviebooking.entity;

public class Seat {
    private String seatNumber;
    private boolean isSelected;

    public Seat(String seatNumber, boolean isSelected) {
        this.seatNumber = seatNumber;
        this.isSelected = isSelected;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
