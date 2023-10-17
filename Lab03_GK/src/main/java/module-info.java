module com.example.lab03_gk {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab03_gk to javafx.fxml;
    exports com.example.lab03_gk;
}