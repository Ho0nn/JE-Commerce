package com.example.demo.Model;

import com.example.demo.Entity.Customer;

import java.sql.*;
import java.time.LocalDate;

public class CustomerModel {
    Connection con = null;
    Statement state = null;

    public CustomerModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/store?user=root&password=");
            state = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addData(Customer cust) {
        String insertData = "insert into customer(id,name,phone,date)values (?,?,?,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(insertData)) {
            preparedStatement.setInt(1, cust.getId());
            preparedStatement.setString(2,cust.getName());
            preparedStatement.setString(3, cust.getPhone());
            LocalDate localDate =cust.getDete() ; // Assuming 'getDate()' returns LocalDate
            preparedStatement.setDate(5, Date.valueOf(localDate));
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
    public void updateData(String phone,String email){
        String updateData = "UPDATE customer SET phone= ? WHERE email = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(updateData)) {
            preparedStatement.setString(1, phone);
            preparedStatement.setString(2, email);
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
        String deleteQuery = "DELETE FROM customer WHERE id = ?";
        try (PreparedStatement preState = con.prepareStatement(deleteQuery)) {
            preState.setInt(1, id);
            preState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
