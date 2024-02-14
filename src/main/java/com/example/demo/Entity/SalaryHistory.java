package com.example.demo.Entity;

import java.time.format.DateTimeFormatter;

public class SalaryHistory {
    private int Id,Emp_Id;
    private double Salary,Deduct,Tot_Sal,Bouns;
    private DateTimeFormatter  date;

    public SalaryHistory(int id, double salary, double deduct,double bouns,double tot_Sal,DateTimeFormatter date,int emp_Id) {
        Id = id;
        Emp_Id = emp_Id;
        Salary = salary;
        Deduct = deduct;
        Tot_Sal = tot_Sal;
        Bouns = bouns;
        this.date = date;
    }

    public double getTot_Sal() {
        return Tot_Sal;
    }

    public void setTot_Sal(double tot_Sal) {
        Tot_Sal = tot_Sal;
    }

    public double getBouns() {
        return Bouns;
    }

    public void setBouns(double bouns) {
        Bouns = bouns;
    }

    public DateTimeFormatter getDate() {
        return date;
    }

    public void setDate(DateTimeFormatter date) {
        this.date = date;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getEmp_Id() {
        return Emp_Id;
    }

    public void setEmp_Id(int emp_Id) {
        Emp_Id = emp_Id;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    public double getDeduct() {
        return Deduct;
    }

    public void setDeduct(double deduct) {
        Deduct = deduct;
    }
}
