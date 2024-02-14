package com.example.demo.Model;

import java.sql.*;

public class Helper {
    Connection con = null;
    Statement state = null;
    public Helper() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/store?user=root&password=");
            state = con.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet fillTable(String tableName) throws SQLException {
        String sc = "select * from " + tableName;
        ResultSet res = state.executeQuery(sc);
        return res;
    }
    static String name;
    static  int id;
    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Helper.name = name;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Helper.id = id;
    }
    static String roleName;

    public static String getRoleName() {
        return roleName;
    }

    public static void setRoleName(String roleName) {
        Helper.roleName = roleName;
    }
   }
