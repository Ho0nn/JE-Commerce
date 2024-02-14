module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    requires java.sql;
    opens com.example.demo to javafx.fxml,javafx.graphics,javafx.controls;
    opens com.example.demo.Entity to javafx.base;
    exports com.example.demo;

}