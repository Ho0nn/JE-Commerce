package com.example.demo.Model;

import com.example.demo.Entity.Employee;

import java.sql.*;

public class EmpModel {
    Connection con = null;
    Statement state = null;
    public EmpModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/store?user=root&password=");
            state = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addData(Employee emp){
        String insertData = "insert into employee(name,phone,email,user_name, password,active, base_salary, role_id)values (?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(insertData)) {
            preparedStatement.setString(1, emp.getName());
            preparedStatement.setString(2, emp.getPhone());
            preparedStatement.setString(3,emp.getEmail());
            preparedStatement.setString(4, emp.getUser_Name());
            preparedStatement.setString(5,emp.getPass());
            preparedStatement.setBoolean(6, true);
            preparedStatement.setDouble(7,emp.getSalary());
            preparedStatement.setInt(8,emp.getRole_Id());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data Inserted Successfully!");
            } else {
                System.out.println("Insertion failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateData(String email,double base_salary){
        String updateData = "UPDATE employee SET base_salary = ? WHERE email = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(updateData)) {
            preparedStatement.setDouble(2, base_salary);
            preparedStatement.setString(1, email);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data Updated!");
            } else {
                System.out.println("Update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteData(int id) {
        String deleteQuery = "DELETE FROM emplyee WHERE id = ?";
        try (PreparedStatement preState = con.prepareStatement(deleteQuery)) {
            preState.setInt(1, id);
            preState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
