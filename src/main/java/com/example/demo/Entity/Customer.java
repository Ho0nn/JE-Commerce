package com.example.demo.Entity;

import java.time.LocalDate;

public class Customer {
    private int Id;
    private String Name,Phone;
    private LocalDate dete;
    public Customer(int id, String name, String phone, LocalDate dete) {
        Id = id;
        Name = name;
        Phone = phone;
        this.dete = dete;
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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public LocalDate getDete() {
        return dete;
    }

    public void setDete(LocalDate dete) {
        this.dete = dete;
    }
}
