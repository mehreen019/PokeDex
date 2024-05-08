module com.example.lab_8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.lab_8 to javafx.fxml;
    exports com.example.lab_8;
}