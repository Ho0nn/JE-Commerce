package com.example.demo.Entity;

import java.time.LocalDate;
public class Sales {
    private int ID,Bill_ID,Cust_ID,Prod_ID;
    private String Sreil_Num,Bill_Num;
  private double Piece_Price,Discount,Tot_Price;
    private LocalDate date;

    public Sales(double piece_Price, double discount, double tot_Price, LocalDate date, int prod_ID, String sreil_Num, int bill_ID, String bill_Num) {
        Prod_ID = prod_ID;
        Sreil_Num = sreil_Num;
        Bill_Num = bill_Num;
        Piece_Price = piece_Price;
        Bill_ID=bill_ID;
        Discount = discount;
        Tot_Price = tot_Price;
        this.date = date;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getBill_ID() {
        return Bill_ID;
    }

    public void setBill_ID(int bill_ID) {
        Bill_ID = bill_ID;
    }

    public int getCust_ID() {
        return Cust_ID;
    }

    public void setCust_ID(int cust_ID) {
        Cust_ID = cust_ID;
    }

    public int getProd_ID() {
        return Prod_ID;
    }

    public void setProd_ID(int prod_ID) {
        Prod_ID = prod_ID;
    }

    public String getSreil_Num() {
        return Sreil_Num;
    }

    public void setSreil_Num(String sreil_Num) {
        Sreil_Num = sreil_Num;
    }

    public String getBill_Num() {
        return Bill_Num;
    }

    public void setBill_Num(String bill_Num) {
        Bill_Num = bill_Num;
    }

    public double getPiece_Price() {
        return Piece_Price;
    }

    public void setPiece_Price(Float piece_Price) {
        Piece_Price = piece_Price;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(Float discount) {
        Discount = discount;
    }

    public double getTot_Price() {
        return Tot_Price;
    }

    public void setTot_Price(Float tot_Price) {
        Tot_Price = tot_Price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
