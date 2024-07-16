package com.example.demo.Entity;

public class Employee {
    private int id, roleId;
    private String userName, pass, phone, email, name;
   private double salary;
    private boolean active;
    public Employee(String name, String phone, String email, String userName, String pass, boolean active, double salary, int roleId) {
        this.roleId = roleId;
        this.userName = userName;
        this.pass = pass;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.salary = salary;
        this.active = true;
    }
    public Employee(){
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
