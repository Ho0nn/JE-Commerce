package com.example.demo.Entity;
public class Category {
    private int id;
    private String name;
    public Category(int id, String name) {
        this.name = name;
        this.id = id;
    }
    public Category() {
        this.name = "";
        this.id = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
