package com.example.demo;

import com.example.demo.Entity.Bill;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.Sales;
import com.example.demo.Model.BillModel;
import com.example.demo.Model.Helper;
import com.example.demo.Model.ProductModel;
import com.example.demo.Model.SalesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class salesController implements Initializable {
    @FXML
    private TextField ProdID;

    @FXML
    private TextField ProdName;
    @FXML
    private TableView<Product> Products;
    ObservableList<Product>prodData;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private Button Save;
    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> amount;

    @FXML
    private TableColumn<?, ?> sell_price;

    @FXML
    private TextField Search;

    @FXML
    private TextField sellPrice;
    @FXML
    private TableView<Product> BillTab;
    ObservableList<Product>Billdata=FXCollections.observableArrayList();

    @FXML
    private TableColumn<Product,Integer> dprodId;

    @FXML
    private TableColumn<Product, String> bprodName;

    @FXML
    private TableColumn<Product, Integer> bprodAmount;

    @FXML
    private TableColumn<Product, Double> bprodPrice;
    @FXML
    private TextField Amount;
    @FXML
    private TextField disc;
    @FXML
    void onAddButtonClick(ActionEvent event) {
        try {
            int ID = Integer.parseInt(ProdID.getText());
            String NAME = ProdName.getText();
            int AMOUNT = Integer.parseInt(Amount.getText());
            Double PRICE = Double.valueOf(sellPrice.getText());
            ProductModel productModel = new ProductModel();
            int am = productModel.selectAmount(ID);
            if (AMOUNT <= am ) {
                Product p = new Product();
                p.setID(ID);
                p.setName(NAME);
                p.setAmount(AMOUNT);
                p.setSellPrice(PRICE);
                if (!isContain(ID)){
                    Billdata.add(p);
                    BillTab.setItems(Billdata);
                    onClearButtonClick();
                }
                else{
                    onClearButtonClick();
                }
            }
            else {
                Amount.setText("NOT Valid");
            }
        }
        catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

    }
    boolean isContain(int ID){
        for (Product prod : Billdata) {
            int idd = prod.getID();
            if (idd==ID)
            return true;
        }
        return false;
    }
    @FXML
    void onClearButtonClick() {
        ProdID.setText("");
        sellPrice.setText("");
        ProdName.setText("");
        Amount.setText("1");
        disc.setText("0.0");
    }
    Connection con = null;
    Statement state = null;
    public void fillTextField(TableView.TableViewSelectionModel<Product> productsSelectionModel) {
        if (!productsSelectionModel.isEmpty()) {
            Product selectedProduct = productsSelectionModel.getSelectedItem();
            ProdID.setText(String.valueOf(selectedProduct.getID()));
            ProdName.setText(selectedProduct.getName());
            sellPrice.setText(String.valueOf(selectedProduct.getSellPrice()));
            Amount.setText(String.valueOf(1));

        }
    }
    public salesController() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/store?user=root&password=");
            state = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void onPrintButtonClick(){

    }
    @FXML
    void onSaveButtonClick() throws IOException {
        double ttot = 0.0;
        for (Product prod : Billdata) {
            ttot += (prod.getSellPrice());
        }
        double discount= Double.parseDouble(disc.getText());
        LocalDate date = LocalDate.now();
        String billnum = "bill-" + (int)(Math.random() * 1000);
        double finall=ttot-(ttot*discount/100);
        int empId=new Helper().getId();
        Bill bill = new Bill(ttot,discount, finall, date, billnum, billnum,empId);
        BillModel bm = new BillModel();
        int x= bm.addData(bill);
        for (Product prod : Billdata){
            int prodId = prod.getID();
            String serialNum = "x" + (int) (Math.random() * 1000);
            double onePrice = prod.getSellPrice();
            int amount = Integer.parseInt(Amount.getText());
            double totPrice = onePrice * amount;
            LocalDate curDate = LocalDate.now();
            Sales sale = new Sales(onePrice, discount, totPrice, curDate, prodId, serialNum, x, billnum);
            SalesModel sm = new SalesModel();
            sm.addData(sale);
        }
        for (Product prod : Billdata) {
            int idd = prod.getID();
            int am= prod.getAmount();
            ProductModel pm=new ProductModel();
            pm.uptoDate(am,idd);
        }
        Stage stage =new Stage();
        stage.setTitle("Bills");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("billView.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        sell_price.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        dprodId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        bprodName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        bprodAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        bprodPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        fillTable();
        Products.setItems(prodData);
        FilteredList<Product> filteredList = new FilteredList<>(prodData, b -> true);
        Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(product -> {
                if (newValue == null || newValue.trim().isEmpty()) {
                    return true;
                }
                String val = newValue.toLowerCase();
                return String.valueOf(product.getID()).contains(val) ||
                        product.getName().toLowerCase().contains(val) || String.valueOf(product.getAmount()).contains(val) ||
                        String.valueOf(product.getSellPrice()).contains(val);
            });
        });
        SortedList<Product> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(Products.comparatorProperty());
        Products.setItems(sortedList);

        TableView.TableViewSelectionModel<Product> selectionModel = Products.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        selectionModel.selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                fillTextField(selectionModel);
            }
        });
    }
    public void fillTable() {
        try {
            ResultSet res = new Helper().fillTable("product");
            prodData = FXCollections.observableArrayList();
            while (res.next()){
                Product p=new Product();
                p.setID(res.getInt(1));
                p.setName(res.getString(2));
                p.setSellPrice(res.getDouble(4));
                p.setAmount(res.getInt(6));
                prodData.add(p);
            }
            Products.setItems(prodData);
        }
        catch (SQLException ex) {
            Logger.getLogger(categoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}