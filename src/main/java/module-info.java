module com.example.aracservisbakim {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.aracservisbakim to javafx.fxml;
    opens com.example.aracservisbakim.model to javafx.fxml;
    exports com.example.aracservisbakim;
    exports com.example.aracservisbakim.controller;
    opens com.example.aracservisbakim.controller to javafx.fxml;
    exports com.example.aracservisbakim.model;
    //opens com.example.aracservisbakim.model to javafx.fxml;
}