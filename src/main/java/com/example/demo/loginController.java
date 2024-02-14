package com.example.demo;

import com.example.demo.Model.Helper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class loginController {
    @FXML
    private Button login;
    @FXML
    private TextField UseName;
    @FXML
    private PasswordField Pass;
    private Connection con = null;
    private Statement state = null;
    public loginController() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/store?user=root&password=");
            state = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
   public void onLoginButtonClick() {
        String username = UseName.getText();
        String password = Pass.getText();
        String data = "SELECT * FROM employee WHERE user_name = ? AND password = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(data)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                Helper help =new Helper();
                help.setName(username);
                help.setId(res.getInt(1));
                help.setRoleName(String.valueOf(res.getInt(8)));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("homeView.fxml"));
                Parent nextPage = loader.load();
                Scene scene = new Scene(nextPage);
                Stage stage = (Stage) login.getScene().getWindow();
                stage.setScene(scene);
            }
            else {
                System.out.println("Invalid login credentials");
            }
        }
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    String getRole( int roleId) throws SQLException {
        String data = "SELECT * FROM employee WHERE id=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(data)) {
            preparedStatement.setInt(1, roleId);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                return res.getString(2);
            }
            return "No Role";
        }
    }
}
