package com.example.demo.Entity;

public class Product {
    private int id, amount, categoryID;
    private String name, serialNum;
    private double buyPrice, sellPrice, discount;

    public Product( String name, double buyPrice, double sellPrice, double discount, int amount, String serialNum, int categoryID) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.serialNum = serialNum;
        this.buyPrice = buyPrice;
        this.discount = discount;
        this.sellPrice = sellPrice;
        this.categoryID = categoryID;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
