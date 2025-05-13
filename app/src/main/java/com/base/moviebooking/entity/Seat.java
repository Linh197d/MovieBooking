package com.base.moviebooking.entity;

public class Seat {
    private int id;
    private String xPosition;
    private int yPosition;
    private int status;
    private boolean isChoosed = false;

    public Seat(int id, String xPosition, int yPosition, int status) {
        this.id = id;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.status = status;

    }

    public boolean getChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getxPosition() {
        return xPosition;
    }

    public void setxPosition(String xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
