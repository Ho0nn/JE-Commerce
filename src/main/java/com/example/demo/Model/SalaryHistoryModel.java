package com.example.demo.Model;

import com.example.demo.Entity.SalaryHistory;

import java.sql.*;
import java.time.format.DateTimeFormatter;

public class SalaryHistoryModel {
    Connection con = null;
    Statement state = null;
    public SalaryHistoryModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/store?user=root&password=");
            state = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addData(SalaryHistory sal) {
        String insertData = "insert into salary_hietory(id,base_salary,deduction,bonus,total_salary,date_time,employee_Id)values (?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(insertData)) {
            preparedStatement.setInt(1, sal.getId());
            preparedStatement.setDouble(2,sal.getSalary());
            preparedStatement.setDouble(3, sal.getDeduct());
            preparedStatement.setDouble(4, sal.getBouns());
            preparedStatement.setDouble(5, sal.getTot_Sal());
            DateTimeFormatter localDate =sal.getDate() ; // Assuming 'getDate()' returns LocalDate
            Date sqlDate = Date.valueOf(String.valueOf(localDate));
            preparedStatement.setDate(6, sqlDate);
            preparedStatement.setInt(7, sal.getEmp_Id());
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
    public void updateData(SalaryHistory sal){
        String updateData = "UPDATE salary_history SET base_salary=?,deduction= ?,bouns=?,total_salary=?, WHERE employee_id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(updateData)) {
            preparedStatement.setDouble(1, sal.getSalary());
            preparedStatement.setDouble(2, sal.getDeduct());
            preparedStatement.setDouble(3, sal.getBouns());
            preparedStatement.setDouble(4, sal.getTot_Sal());
            preparedStatement.setInt(5, sal.getEmp_Id());
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
        String deleteQuery = "DELETE FROM salary_history WHERE id = ?";
        try (PreparedStatement preState = con.prepareStatement(deleteQuery)) {
            preState.setInt(1, id);
            preState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
