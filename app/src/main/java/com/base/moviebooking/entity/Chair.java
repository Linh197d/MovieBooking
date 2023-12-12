package com.base.moviebooking.entity;

public class Chair {
    private int id;
    private int ticket_id;
    private int chair_id;

    public Chair(int id, int ticket_id, int chair_id) {
        this.id = id;
        this.ticket_id = ticket_id;
        this.chair_id = chair_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getChair_id() {
        return chair_id;
    }

    public void setChair_id(int chair_id) {
        this.chair_id = chair_id;
    }
}
