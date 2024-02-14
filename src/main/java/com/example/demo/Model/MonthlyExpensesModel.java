package com.example.demo.Model;

import com.example.demo.Entity.MonthlyExpenses;

import java.sql.*;
import java.time.format.DateTimeFormatter;

public class MonthlyExpensesModel {
    Connection con = null;
    Statement state = null;
    public MonthlyExpensesModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/store?user=root&password=");
            state = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addData(MonthlyExpenses mea) {
        String insertData = "insert into monthly_expenses(id,name,date_time,notice)values (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(insertData)) {
            preparedStatement.setInt(1,mea.getId());
            preparedStatement.setString(2,mea.getName());
            preparedStatement.setDouble(3, mea.getPrice());
            DateTimeFormatter localDate =mea.getDate() ; // Assuming 'getDate()' returns LocalDate
            Date sqlDate = Date.valueOf(String.valueOf(localDate));
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setString(5,mea.getNotice());
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
    public void updateData(MonthlyExpenses mea){
        String updateData = "UPDATE monthly_expenses SET price=?,notice= ? WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(updateData)) {
            preparedStatement.setDouble(1, mea.getPrice());
            preparedStatement.setString(2, mea.getNotice());
            preparedStatement.setInt(3, mea.getId());
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
        String deleteQuery = "DELETE FROM monthly_expenses WHERE id = ?";
        try (PreparedStatement preState = con.prepareStatement(deleteQuery)) {
            preState.setInt(1, id);
            preState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
