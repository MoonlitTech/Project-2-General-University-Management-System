module com.example.universitymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.universitymanagementsystem to javafx.fxml;
    exports com.example.universitymanagementsystem;
    exports com.example.universitymanagementsystem.model;
    exports com.example.universitymanagementsystem.gui;
}
