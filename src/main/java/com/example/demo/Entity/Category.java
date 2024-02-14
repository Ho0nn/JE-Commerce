package com.example.demo.Entity;
public class Category {
    private int id;
    private String Name;
    public Category(int id, String Name) {
        this.Name = Name;
        this.id = id;
    }
    public Category() {
        this.Name = "";
        this.id = 0;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
