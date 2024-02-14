package com.example.demo.Model;

import com.example.demo.Entity.Product;

import java.sql.*;

public class ProductModel {
    Connection con = null;
    Statement state = null;

    public ProductModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/store?user=root&password=");
            state = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addData(Product prod) {
        String insertData = "INSERT INTO product( name, buy_Price, sell_Price, discount, amount, serial_number, category_id) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(insertData)) {
            preparedStatement.setString(1, prod.getName());
            preparedStatement.setDouble(2, prod.getBuyPrice());
            preparedStatement.setDouble(3, prod.getSellPrice());
            preparedStatement.setDouble(4, prod.getDiscount());
            preparedStatement.setInt(5, prod.getAmount());
            preparedStatement.setString(6, prod.getSerialNum());
            preparedStatement.setInt(7, prod.getCategoryID());
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

    public void updateData(int id, double discount, double Buy_Price, double Sell_Price) {
        String updateData = "UPDATE product SET discount = ?,Buy_Price=?,Sell_Price=? WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(updateData)) {
            preparedStatement.setDouble(1, discount);
            preparedStatement.setDouble(2, Buy_Price);
            preparedStatement.setDouble(3, Sell_Price);
            preparedStatement.setInt(4, id);
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

    public void uptoDate(double am, int idd) {
        String updateData = "UPDATE product SET amount=(amount-?) WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(updateData)) {
            preparedStatement.setDouble(1, am);
            preparedStatement.setInt(2, idd);
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

    public int selectAmount(int productId) {
        int amount = 0;
        try {
            String query = "SELECT amount FROM product WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                amount = resultSet.getInt("amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }

    public void deleteData(int index) {
        String deleteQuery = "DELETE FROM product WHERE id = ?";
        try (PreparedStatement preState = con.prepareStatement(deleteQuery)) {
            preState.setInt(1, index);
            preState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}