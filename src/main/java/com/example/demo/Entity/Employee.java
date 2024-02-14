package com.example.demo.Entity;

public class Employee {
    private int Id,role_Id;
    private String user_Name,Pass,Phone,Email,Name;
   private double Salary;
    private boolean Active;
    public Employee( String name,String phone, String email, String user_Name, String pass,boolean active, double salary,  int role_Id) {
        this.role_Id = role_Id;
        this.user_Name = user_Name;
        Pass = pass;
        Phone = phone;
        Email = email;
        Name = name;
        Salary = salary;
        Active = true;
    }
    public Employee(){
        Id = 0;
        this.role_Id = 0;
        this.user_Name = "";
        Pass = "";
        Phone = "";
        Email = "";
        Name = "";
        Salary = 0.0;
        Active = true;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getRole_Id() {
        return role_Id;
    }

    public void setRole_Id(int role_Id) {
        this.role_Id = role_Id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }
}
