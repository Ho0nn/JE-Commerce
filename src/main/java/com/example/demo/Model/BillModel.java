package com.example.demo.Model;

import com.example.demo.Entity.Bill;

import java.sql.*;
import java.time.LocalDate;

public class BillModel {
    Connection con = null;
    Statement state = null;

    public BillModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/store?user=root&password=");
            state = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int addData(Bill bill) {
        String insertData = "insert into bill(total_price,discount,total_price_with_discount,date_time,bill_number,bill_number_pre_day,employee_id) values (?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(insertData, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, bill.getTot_Price());
            preparedStatement.setDouble(2, bill.getDisc());
            preparedStatement.setDouble(3, bill.getFinal_Tot());
            LocalDate localDate = bill.getDate();
            Date sqlDate = Date.valueOf(localDate);
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setString(5, bill.getBill_Num());
            preparedStatement.setString(6, bill.getDay_Bill_Num());
            preparedStatement.setInt(7, bill.getEmp_Id());


            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data Inserted Successfully!");

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        return generatedId;
                    }
                }
            } else {
                System.out.println("Insertion failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
