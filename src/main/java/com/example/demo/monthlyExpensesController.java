package com.example.demo;

import com.example.demo.Entity.MonthlyExpenses;
import com.example.demo.Model.Helper;
import com.example.demo.Model.MonthlyExpensesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class monthlyExpensesController implements Initializable {

    @FXML
    private TextField ExpenseID;

    @FXML
    private TextField ExpenseName;

    @FXML
    private TableView<MonthlyExpenses> METab;

    @FXML
    private TableColumn<MonthlyExpenses, Integer> id;

    @FXML
    private TableColumn<MonthlyExpenses, String> name;

    @FXML
    private TextField Pricee;
ObservableList<MonthlyExpenses>MEdata;
    @FXML
    private TableColumn<MonthlyExpenses, DateTimeFormatter> Date;

    @FXML
    private TableColumn<MonthlyExpenses, String> Notes;

    @FXML
    private TableColumn<MonthlyExpenses, String> Price;
    @FXML
    private TextField Search;

    @FXML
    private TextField Notese;
    public void con() {
        Connection con = null;
        Statement state = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/store?user=root&password=");
            state = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAddButtonClick() {
        String EXname=ExpenseName.getText();
        double Price= Double.parseDouble(Pricee.getText());
        String notes=Notese.getText();
        LocalDate date = LocalDate.now();
        DateTimeFormatter curDate = DateTimeFormatter.ofPattern(String.valueOf(date));
        //String EXdata= METab.getSelectionModel().getSelectedItem().getName();
        MonthlyExpenses me=new MonthlyExpenses(EXname,Price,curDate,notes);
        MonthlyExpensesModel mm=new MonthlyExpensesModel();
        mm.addData(me);
        fillTable();
    }
    @FXML
    void onClearButtonClick() {
        ExpenseID.setText("");
        ExpenseName.setText("");
        Notese.setText("");
        Pricee.setText("");
        Date.setText("");
        fillTable();
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("ExpenseID"));
        name.setCellValueFactory(new PropertyValueFactory<>("ExpenseName"));
        Notes.setCellValueFactory(new PropertyValueFactory<>("Notes"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Pricee"));
        LocalDate date = LocalDate.now();
         DateTimeFormatter curDate = DateTimeFormatter.ofPattern(String.valueOf(date));
        Date.setCellValueFactory(new PropertyValueFactory<>("curDate"));
        fillTable();
        METab.setItems(MEdata);
        FilteredList<MonthlyExpenses> filteredList=new FilteredList<>(MEdata, b->true);
        Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(month -> {
                if (newValue == null || newValue.trim().isEmpty()) {
                    return true;
                }
                String val = newValue.toLowerCase();
                return String.valueOf(month.getId()).contains(val) ||
                        month.getName().toLowerCase().contains(val)||month.getNotice().toLowerCase().contains(val)||
                        String.valueOf(month.getDate()).contains(val)|| String.valueOf(month.getPrice()).contains(val);
            });
        });

        SortedList<MonthlyExpenses> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(METab.comparatorProperty());
        METab.setItems(sortedList);
    }
    public void fillTable() {
        try {
            ResultSet res = new Helper().fillTable("monthly_expenses");
            MEdata = FXCollections.observableArrayList();
            while (res.next())
            {
                MonthlyExpenses m=new MonthlyExpenses();
                m.setId(res.getInt(1));
                m.setName(res.getString(2));
                m.setPrice(res.getDouble(3));
                LocalDate date = LocalDate.now();
                DateTimeFormatter curDate = DateTimeFormatter.ofPattern(String.valueOf(date));
                m.setDate(curDate);
                m.setNotice(res.getString(5));
                MEdata.add(m);
            }
            METab.setItems(MEdata);
        }
        catch (SQLException ex) {
            Logger.getLogger(categoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}