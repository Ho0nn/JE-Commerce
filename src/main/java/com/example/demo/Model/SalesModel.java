package com.example.demo.Model;

import com.example.demo.Entity.Sales;

import java.sql.*;
import java.time.LocalDate;

public class SalesModel {
    Connection con = null;
    Statement state = null;

    public SalesModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/store?user=root&password=");
            state = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addData(Sales sales) {
        String insertData = "INSERT INTO sales(price_per_one, discount, total_price, date_time, product_id, serial_number, bill_id, bill_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(insertData)) {
            preparedStatement.setDouble(1, sales.getPiece_Price());
            preparedStatement.setDouble(2, sales.getDiscount());
            preparedStatement.setDouble(3, sales.getTot_Price());
            LocalDate localDate = sales.getDate();
            Date sqlDate = Date.valueOf(localDate);
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setInt(5, sales.getProd_ID());
            preparedStatement.setString(6, sales.getSreil_Num());
            preparedStatement.setInt(7, sales.getBill_ID());
            preparedStatement.setString(8, sales.getBill_Num());
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

    public void updateData(Sales sales) {
        String updateData = "UPDATE sales SET price_per_one=?, discount=?, total_price=? WHERE id=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(updateData)) {
            preparedStatement.setDouble(1, sales.getPiece_Price());
            preparedStatement.setDouble(2, sales.getDiscount());
            preparedStatement.setDouble(3, sales.getTot_Price());
            preparedStatement.setInt(4, sales.getID());
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
        String deleteQuery = "DELETE FROM sales WHERE id = ?";
        try (PreparedStatement preState = con.prepareStatement(deleteQuery)) {
            preState.setInt(1, id);
            preState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
