package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class homeController {

    Pane veiw;
    @FXML
    Pane viewPane;
    @FXML
    Pane pane;

    @FXML
    void goHome(ActionEvent event) {
        pane.getChildren().add(getPane("homeView.fxml"));
    }
    @FXML
    void goEmployee(ActionEvent event) {
        viewPane.getChildren().add(getPane("employeeView.fxml"));
    }

    @FXML
    void goCustomer(ActionEvent event) {
        viewPane.getChildren().add(getPane("customerView.fxml"));

    }
    @FXML
    void goRoles(){
        viewPane.getChildren().add(getPane("roleView.fxml"));
    }
    @FXML
    void goExpenses(){
        viewPane.getChildren().add(getPane("meView.fxml"));

    }
    @FXML
    public void goSales(ActionEvent event) {
        viewPane.getChildren().add(getPane("salesView.fxml"));
    }
    public void goBill() {
        viewPane.getChildren().add(getPane("billView.fxml"));
    }

    public void goProduct() {
        viewPane.getChildren().add(getPane("productView.fxml"));
    }

    public Pane getPane(String FXMLname) {
        try {
            veiw = FXMLLoader.load(getClass().getResource(FXMLname));
        } catch (IOException ex) {
        }
        return veiw;
    }

    @FXML
    void goCategory(ActionEvent event) {
        viewPane.getChildren().add(getPane("categoryView.fxml"));

    }

}
