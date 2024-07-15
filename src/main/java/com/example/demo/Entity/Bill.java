package com.example.demo.Entity;

import java.time.LocalDate;

public class Bill{
    private int id, empId;
    private double totPrice, disc, finalTot;
    private LocalDate date;
    private String billNum, dayBillNum;
    public Bill( double totPrice, double disc, double finalTot, LocalDate date, String billNum, String dayBillNum,int empId ) {
        this.empId = empId;
        this.totPrice = totPrice;
        this.disc = disc;
        this.finalTot = finalTot;
        this.date = date;
        this.billNum = billNum;
        this.dayBillNum = dayBillNum;
    }
    public Bill(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public double getTotPrice() {
        return totPrice;
    }

    public void setTotPrice(double totPrice) {
        this.totPrice = totPrice;
    }

    public double getDisc() {
        return disc;
    }

    public void setDisc(double disc) {
        this.disc = disc;
    }

    public double getFinalTot() {
        return finalTot;
    }

    public void setFinalTot(double finalTot) {
        this.finalTot = finalTot;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate Date) {
        date = Date;
    }

    public String getBillNum() {
        return billNum;
    }

    public void setBillNum(String billNum) {
        this.billNum = billNum;
    }

    public String getDayBillNum() {
        return dayBillNum;
    }

    public void setDayBillNum(String dayBillNum) {
        this.dayBillNum = dayBillNum;
    }

}
