package com.example.demo.Model;

import com.example.demo.Entity.Category;

import java.sql.*;

public class CategoryModel {
    Connection con = null;
    Statement state = null;
    public CategoryModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/store?user=root&password=");
            state = con.createStatement();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public void addData(Category Cat) {
        String insertData = "insert into category(name)values (?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(insertData)) {
            preparedStatement.setString(1,Cat.getName());
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
    public void updateData(int id,String data){
        String updateData = "UPDATE category SET name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(updateData)) {
            preparedStatement.setString(1, data);
            preparedStatement.setInt(2, id);
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
        String deleteQuery = "DELETE FROM category WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
                   }
        catch (SQLException e) {
            System.out.println("NO Matched Data!");
        }
    }

}


