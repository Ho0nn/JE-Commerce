package com.example.demo.Entity;

import java.time.format.DateTimeFormatter;

public class SalaryHistory {
    private int id, empId;
    private double salary, deduct, totSal, bouns;
    private DateTimeFormatter  date;

    public SalaryHistory(int id, double salary, double deduct,double bouns,double totSal,DateTimeFormatter date,int empId) {
        this.id = id;
        this.empId = empId;
        this.salary = salary;
        this.deduct = deduct;
        this.totSal = totSal;
        this.bouns = bouns;
        this.date = date;
    }

    public double getTotSal() {
        return totSal;
    }

    public void setTotSal(double totSal) {
        this.totSal = totSal;
    }

    public double getBouns() {
        return bouns;
    }

    public void setBouns(double bouns) {
        this.bouns = bouns;
    }

    public DateTimeFormatter getDate() {
        return date;
    }

    public void setDate(DateTimeFormatter date) {
        this.date = date;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getDeduct() {
        return deduct;
    }

    public void setDeduct(double deduct) {
        this.deduct = deduct;
    }
}
