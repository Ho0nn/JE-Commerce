package com.example.demo.Controller;

import com.example.demo.Entity.Product;
import com.example.demo.Model.Helper;
import com.example.demo.Model.ProductModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductController implements Initializable {
    @FXML
    private TextField ProdID;

    @FXML
    private TextField ProdName;

    @FXML
    private TableView<Product> Products;

    @FXML
    private TableColumn<Product,Integer> id;

    @FXML
    private TableColumn<Product, String> name;

    @FXML
    private TableColumn<Product, Double> buy_price;

    @FXML
    private TableColumn<Product, Double> sell_price;

    @FXML
    private TableColumn<Product, String> serial_number;

    @FXML
    private TableColumn<Product, Integer> amount;

    @FXML
    private TableColumn<Product, Double> discount;

    @FXML
    private TableColumn<Product, Integer> category_id;

    @FXML
    private TextField Search;

    @FXML
    private TextField SellPrice;
    @FXML
    private TextField SerNum;
    @FXML
    private TextField BuyPrice;
    @FXML
    ObservableList<Product>prodData;
    @FXML
    private ComboBox<?> CategID;

    @FXML
    private TextField Amount;
    @FXML
    private TextField Discount;
  @FXML   public  Connection con() {
        Connection con = null;
        Statement state = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/store?user=root&password=");
            state = con.createStatement();
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void combo(){
      String selectall="select id from category";
        Connection connect = con();
      try {
          PreparedStatement statement=connect.prepareStatement(selectall);
          ResultSet result = statement.executeQuery();
          ObservableList list =FXCollections.observableArrayList();
          while (result.next()){
              int item=result.getInt("id");
              list.add(item);
          }
          CategID.setItems(list);
      }
      catch (SQLException e) {
          throw new RuntimeException(e);
      }
    }
        @FXML
        protected void onInsertButtonClick() {
            String prodName=ProdName.getText();
            int amount= Integer.parseInt(Amount.getText());
            Double discount= Double.valueOf(Discount.getText());
            double buyPrice= Double.parseDouble(BuyPrice.getText());
            double sellPrice= Double.parseDouble(SellPrice.getText());
            String serialNum=SerNum.getText();
           int catID= (int) CategID.getSelectionModel().getSelectedItem();
            Product prod=new Product(prodName,buyPrice,sellPrice,discount,amount,serialNum,catID);
            ProductModel pm=new ProductModel();
            pm.addData(prod);
            fillTable();
        }
    @FXML
    void onClearButtonClick() {
        ProdID.setText("");
        ProdName.setText("");
        Amount.setText("");
        Discount.setText("");
        BuyPrice.setText("");
        SellPrice.setText("");
        CategID.setAccessibleText("");
        SerNum.setText("");
        fillTable();
    }
    @FXML
    void onDeleteButtonClick() {
        try {
            int ID = Integer.parseInt(ProdID.getText());
            ProductModel pm = new ProductModel();
            pm.deleteData(ID);
            fillTable();
            System.out.println("Data deleted successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID ! Please enter a valid numeric ID.");
        } catch (Exception e) {
            System.out.println("An error occurred while deleting data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    void onUpdateButtonClick() {
      String idd=ProdID.getText();
        int ID= Integer.parseInt(idd);
        double dis= Double.parseDouble(Discount.getText());
        double Bp= Double.parseDouble(BuyPrice.getText());
        double Sp=Double.parseDouble(SellPrice.getText());
        ProductModel pm=new ProductModel();
        pm.updateData(ID,dis,Bp,Sp);
        fillTable();
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        buy_price.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));
        sell_price.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
       serial_number.setCellValueFactory(new PropertyValueFactory<>("serialNum"));
        category_id.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        fillTable();
        Products.setItems(prodData);
        FilteredList<Product> filteredList=new FilteredList<>(prodData, b->true);
        Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(product -> {
                if (newValue == null || newValue.trim().isEmpty()) {
                    return true;
                }
                String val = newValue.toLowerCase();
                return String.valueOf(product.getId()).contains(val) ||
                        product.getName().toLowerCase().contains(val)|| String.valueOf(product.getAmount()).contains(val)||
                        String.valueOf(product.getCategoryID()).contains(val)|| String.valueOf(product.getDiscount()).contains(val)
                        || String.valueOf(product.getBuyPrice()).contains(val) || String.valueOf(product.getSellPrice()).contains(val)
                        ||  product.getSerialNum().contains(val);
            });
        });

        SortedList<Product> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(Products.comparatorProperty());
        Products.setItems(sortedList);
        combo();
    }
    public void fillTable() {
        try {
            ResultSet res = new Helper().fillTable("product");
            prodData = FXCollections.observableArrayList();
            while (res.next())
            {
                Product p=new Product();
                p.setId(res.getInt(1));
                p.setName(res.getString(2));
                p.setBuyPrice(res.getDouble(3));
                p.setSellPrice(res.getDouble(4));
                p.setDiscount(res.getDouble(5));
                p.setAmount(res.getInt(6));
                p.setSerialNum(res.getString(7));
                p.setCategoryID(res.getInt(8));
                prodData.add(p);
            }
            Products.setItems(prodData);
        }
        catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}