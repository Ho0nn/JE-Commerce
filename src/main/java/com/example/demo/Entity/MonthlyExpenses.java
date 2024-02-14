package com.example.demo.Entity;
import java.time.format.DateTimeFormatter;
public class MonthlyExpenses {
    private int Id;
    private String Name,Notice;
    private double Price;
    private DateTimeFormatter date;

    public MonthlyExpenses( String name, double price, DateTimeFormatter date,String Notic) {
        this.Id = Id;
        Name = name;
        Price = price;
        this.date = date;
        this.Notice=Notic;
    }

    public MonthlyExpenses() {
        Id = 0;
        Name = "";
        Price = 0.0;
        date = null;
        Notice="";
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

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public DateTimeFormatter getDate() {
        return date;
    }

    public void setDate(DateTimeFormatter date) {
        this.date = date;
    }

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String notice) {
        Notice = notice;
    }
}
