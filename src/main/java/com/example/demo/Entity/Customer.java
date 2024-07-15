package com.example.demo.Entity;

import java.time.LocalDate;

public class Customer {
    private int id;
    private String name, phone;
    private LocalDate date;
    public Customer(int id, String name, String phone, LocalDate date) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.date = date;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
