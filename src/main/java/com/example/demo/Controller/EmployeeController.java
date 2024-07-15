package com.example.demo.Controller;

import com.example.demo.Entity.Employee;
import com.example.demo.Model.EmpModel;
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
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeController implements Initializable {

    @FXML
    private TextField EmpID;

    @FXML
    private TextField EmpName;

    @FXML
    private TableView<Employee> EmpTable;

    @FXML
    public ObservableList<Employee> Empdata;

    @FXML
    private TableColumn<Employee, Integer> id;

    @FXML
    private TableColumn<Employee, String> name;

    @FXML
    private TableColumn<Employee, String> phone;

    @FXML
    private TableColumn<Employee, String> email;

    @FXML
    private TableColumn<Employee, String> pass;

    @FXML
    private ComboBox<?> RollD;

    @FXML
    private TableColumn<Employee, String> user_name;

    @FXML
    private TableColumn<Employee, Double> salary;

    @FXML
    private TableColumn<Employee, Integer> role_id;
    @FXML
    private TextField Search;

    @FXML
    private TextField Username;

    @FXML
    private TextField Phone;

    @FXML
    private TextField Email;

    @FXML
    private TextField EmpSalary;

    @FXML
    private PasswordField Password;

     public Connection con() {
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
        String selectall="select id from role";
        Connection connect = con();
        try {
            PreparedStatement statement=connect.prepareStatement(selectall);
            ResultSet result = statement.executeQuery();
            ObservableList list =FXCollections.observableArrayList();
            while (result.next()){
                int item=result.getInt("id");
                list.add(item);
            }
            RollD.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onInsertButtonClick() {
        try {
            String empName = EmpName.getText();
            String email = Email.getText();
            String phone = Phone.getText();
            String Pass = Password.getText();
            String User = Username.getText();
            Double Salary = Double.valueOf(EmpSalary.getText());
            int roleid = (int) RollD.getSelectionModel().getSelectedItem();
            Employee emp = new Employee(empName, phone, email, User, Pass,true, Salary, roleid);
            EmpModel em = new EmpModel();
            em.addData(emp);
            fillTable();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void onClearButtonClick() {
        EmpID.setText("");
        EmpName.setText("");
        Phone.setText("");
        Password.setText("");
        Username.setText("");
        Email.setText("");
        RollD.setAccessibleText("");
        EmpSalary.setText("");
        fillTable();
    }

    @FXML
    void onDeleteButtonClick() {
    if (!EmpID.getText().isEmpty()) {
            int ID= Integer.parseInt(EmpID.getText());
            EmpModel em = new EmpModel();
            em.deleteData(ID);
            fillTable();
            System.out.println("Data deleted successfully!");
        } else {
            System.out.println("Please select a row to delete.");
        }
    }

    @FXML
    void onUpdateButtonClick() {
        String em = Email.getText();
        double sal = Double.parseDouble(EmpSalary.getText());
        EmpModel emp = new EmpModel();
        emp.updateData(em, sal);
        fillTable();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        pass.setCellValueFactory(new PropertyValueFactory<>("password"));
        user_name.setCellValueFactory(new PropertyValueFactory<>("userName"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        role_id.setCellValueFactory(new PropertyValueFactory<>("roleId"));
        fillTable();

        FilteredList<Employee> filteredList = new FilteredList<>(Empdata, b -> true);
        Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(employee -> {
                if (newValue == null || newValue.trim().isEmpty()) {
                    return true;
                }
                String val = newValue.toLowerCase();
                return String.valueOf(employee.getId()).contains(val) ||
                        employee.getName().toLowerCase().contains(val) || String.valueOf(employee.getRoleId()).contains(val) ||
                        String.valueOf(employee.getSalary()).contains(val) || employee.getUserName().toLowerCase().contains(val)
                        || String.valueOf(employee.getPhone()).contains(val) || employee.getEmail().contains(val);
            });
        });
        SortedList<Employee> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(EmpTable.comparatorProperty());
        EmpTable.setItems(sortedList);
        combo();
    }

    public void fillTable() {
        try {
            ResultSet res = new Helper().fillTable("employee");
            Empdata = FXCollections.observableArrayList();
            while (res.next()) {
                Employee emp = new Employee();
                emp.setId(res.getInt(1));
                emp.setName(res.getString(2));
                emp.setPhone(res.getString(3));
                emp.setEmail(res.getString(4));
                emp.setPass(res.getString(6));
                emp.setUserName(res.getString(5));
                emp.setSalary(res.getDouble(8));
                emp.setRoleId(res.getInt(9));
                Empdata.add(emp);
            }
            EmpTable.setItems(Empdata);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
