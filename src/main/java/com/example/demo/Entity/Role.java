package com.example.demo.Entity;

public class Role {
    private int Id;
    private String Name;

    public Role(int Id,String Name) {
        this.Id = Id;
        this.Name=Name;
    }
    public Role() {
        this.Id = 0;
        this.Name="";
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
