package com.example.demo.Entity;
import java.time.format.DateTimeFormatter;
public class MonthlyExpenses {
    private int id;
    private String name, notice;
    private double price;
    private DateTimeFormatter date;

    public MonthlyExpenses( String name, double price, DateTimeFormatter date,String notic) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.notice =notic;
    }

    public MonthlyExpenses() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public DateTimeFormatter getDate() {
        return date;
    }

    public void setDate(DateTimeFormatter date) {
        this.date = date;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
