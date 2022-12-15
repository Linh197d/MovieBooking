package com.base.mvvmbaseproject.entity;

import java.util.ArrayList;

public class ContenTest {
    private int id;
    private ArrayList<Content> content;
    private String title;
    private String file_name;

    public ContenTest(int id, ArrayList<Content> content, String title, String file_name) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.file_name = file_name;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Content> getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getFile_name() {
        return file_name;
    }
}
