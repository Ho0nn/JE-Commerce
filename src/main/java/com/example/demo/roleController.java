package com.example.demo;

import com.example.demo.Entity.Role;
import com.example.demo.Model.Helper;
import com.example.demo.Model.RoleModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class roleController implements Initializable {
    @FXML
    private Button Insert;

    @FXML
    private TextField name;

    @FXML
    private TextField id;

    @FXML
    private Button Clear;

    @FXML
    private Button Update;

    @FXML
    private Button Delete;

    @FXML
    private Label Role;

    @FXML
    private TableView<Role> RoleTable;

    @FXML
    private TableColumn<Role, Integer> role_id;

    @FXML
    private TableColumn<Role, String> role_name;
    @FXML
    ObservableList<Role>Roledata;
    @FXML
    private Label Lname;

    @FXML
    private Label Lid;

    @FXML
    private TextField Search;

    @FXML
    private Button SearchBtn;

    @FXML
    protected void onInsertButtonClick() {
        String RoleName=name.getText();
        if (RoleName != null && !RoleName.trim().isEmpty()) {
            Role rol = new Role();
            rol.setName(RoleName);
            RoleModel rm = new RoleModel();
            rm.addData(rol);
            fillTable();
        }
        else{
            System.out.println("Please enter a valid Role name.");
        }
    }
    @FXML
    protected void onDeleteButtonClick() {
        try {
            int ID = Integer.parseInt(id.getText());
             RoleModel rm = new RoleModel();
            rm.deleteData(ID);
            fillTable();
            System.out.println("Data deleted successfully!");
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a valid numeric ID.");
        } catch (Exception e) {
            System.out.println("An error occurred while deleting data: " + e.getMessage());
            e.printStackTrace();

        }
    }

    @FXML
    protected void onUpdateButtonClick() {
        int ID= Integer.parseInt(id.getText());
        String RoleName=name.getText();
        RoleModel rm=new RoleModel();
        rm.updateData(ID,RoleName);
        fillTable();
    }
    @FXML
    protected void onClearButtonClick() {
        id.setText("");
        name.setText("");
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        role_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        role_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        fillTable();
    }
    public void fillTable() {
        try {

            ResultSet res = new Helper().fillTable("role");
            Roledata = FXCollections.observableArrayList();
            while (res.next())
            {
                Role r=new Role();
                r.setId(res.getInt(1));
                r.setName(res.getString(2));
                Roledata.add(r);
            }
            RoleTable.setItems(Roledata);
            FilteredList<Role> filteredList=new FilteredList<>(Roledata, b->true);
            Search.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(role -> {
                    if (newValue == null || newValue.trim().isEmpty()) {
                        return true;
                    }
                    String val = newValue.toLowerCase();
                    return String.valueOf(role.getId()).contains(val) ||
                            role.getName().toLowerCase().contains(val);
                });
            });

            SortedList<Role> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(RoleTable.comparatorProperty());
            RoleTable.setItems(sortedList);
        } catch (SQLException ex) {
            Logger.getLogger(categoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void onSearchButtonClick() {
    }
}

