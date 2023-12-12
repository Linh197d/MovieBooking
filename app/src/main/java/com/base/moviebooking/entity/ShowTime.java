package com.base.moviebooking.entity;

import java.io.Serializable;
import java.sql.Array;

public class ShowTime  {
    private int id;
    private String name;
    private String[] array;

    public ShowTime(int id, String name, String[] array) {
        this.id = id;
        this.name = name;
        this.array = array;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }
}
