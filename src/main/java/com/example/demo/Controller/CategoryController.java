package com.example.demo.Controller;

import com.example.demo.Entity.Category;
import com.example.demo.Model.CategoryModel;
import com.example.demo.Model.Helper;
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

public class CategoryController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private Button Insert;

    @FXML
    private Label lname;

    @FXML
    private Label lid;

    @FXML
    private Button Clear;

    @FXML
    private Button Update;
    @FXML
    ObservableList<Category> Catdata;
    @FXML
    TableView<Category>CategTable;
    @FXML
    private Button Delete;
    @FXML
    private Label Category;
    @FXML
    private Button SearchBtn;
    @FXML
    private TextField Search;

    @FXML
    private TableColumn<Category,Integer> CategID;
    @FXML
    private TableColumn<Category,String> CategName;
    @FXML
    protected void onInsertButtonClick() {
        String CatName=name.getText();
        if (CatName != null && !CatName.trim().isEmpty()) {
            Category cat = new Category();
            cat.setName(CatName);
            CategoryModel cm = new CategoryModel();
            cm.addData(cat);
            fillTable();
        }
        else{
            System.out.println("Please enter a valid category name.");
        }
    }
    @FXML
    protected void onDeleteButtonClick() {
        try {
            int ID = Integer.parseInt(id.getText());
            CategoryModel cm = new CategoryModel();
            cm.deleteData(ID);
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
        String CatName=name.getText();
        CategoryModel cm=new CategoryModel();
        cm.updateData(ID,CatName);
        fillTable();
    }
    @FXML
    protected void onClearButtonClick() {
        id.setText("");
        name.setText("");
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategID.setCellValueFactory(new PropertyValueFactory<>("id"));
        CategName.setCellValueFactory(new PropertyValueFactory<>("name"));
        fillTable();
    }
    public void fillTable() {
        try {

            ResultSet res = new Helper().fillTable("category");
            Catdata = FXCollections.observableArrayList();
            while (res.next())
            {
                Category c=new Category();
                c.setId(res.getInt(1));
                c.setName(res.getString(2));
                Catdata.add(c);
            }
            CategTable.setItems(Catdata);
            FilteredList<Category> filteredList=new FilteredList<>(Catdata, b->true);
            Search.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(category -> {
                    if (newValue == null || newValue.trim().isEmpty()) {
                        return true;
                    }
                    String val = newValue.toLowerCase();
                    return String.valueOf(category.getId()).contains(val) ||
                            category.getName().toLowerCase().contains(val);
                });
            });

            SortedList<Category> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(CategTable.comparatorProperty());
            CategTable.setItems(sortedList); // Set sorted data to your TableView
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onSearchButtonClick() {
        String searchText = Search.getText().toLowerCase();

    }
}
