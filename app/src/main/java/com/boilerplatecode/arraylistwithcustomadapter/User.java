package com.boilerplatecode.arraylistwithcustomadapter;

public class User {
    private String row_id;
    private  String name;
    private String note;

    public User(String row_id, String name, String note) {
        this.row_id = row_id;
        this.name = name;
        this.note = note;
    }

    public String getRow_id() {
        return row_id;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public void setRow_id(String row_id) {
        this.row_id = row_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
