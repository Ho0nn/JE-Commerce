package com.example.demo.Entity;

import java.time.LocalDate;

public class Bill{
    private int id,Emp_Id;
    private double Tot_Price,Disc,Final_Tot;
    private LocalDate date;
    private String Bill_Num,Day_Bill_Num;
    public Bill( double tot_Price, double disc, double final_Tot, LocalDate date, String bill_Num, String day_Bill_Num,int emp_Id ) {
        Emp_Id = emp_Id;
        Tot_Price = tot_Price;
        Disc = disc;
        Final_Tot = final_Tot;
        this.date = date;
        Bill_Num = bill_Num;
        Day_Bill_Num = day_Bill_Num;
    }
    public Bill(){
        id = 0;
        Emp_Id = 0;
        Tot_Price = 0;
        Disc = 0;
        Final_Tot =0;
      date=getDate();
        Bill_Num = "";
        Day_Bill_Num = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmp_Id() {
        return Emp_Id;
    }

    public void setEmp_Id(int emp_Id) {
        Emp_Id = emp_Id;
    }

    public double getTot_Price() {
        return Tot_Price;
    }

    public void setTot_Price(double tot_Price) {
        Tot_Price = tot_Price;
    }

    public double getDisc() {
        return Disc;
    }

    public void setDisc(double disc) {
        Disc = disc;
    }

    public double getFinal_Tot() {
        return Final_Tot;
    }

    public void setFinal_Tot(double final_Tot) {
        Final_Tot = final_Tot;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate Date) {
        date = Date;
    }

    public String getBill_Num() {
        return Bill_Num;
    }

    public void setBill_Num(String bill_Num) {
        Bill_Num = bill_Num;
    }

    public String getDay_Bill_Num() {
        return Day_Bill_Num;
    }

    public void setDay_Bill_Num(String day_Bill_Num) {
        Day_Bill_Num = day_Bill_Num;
    }

}
