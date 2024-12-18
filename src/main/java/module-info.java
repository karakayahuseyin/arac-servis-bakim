module com.example.aracservisbakim {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aracservisbakim to javafx.fxml;
    exports com.example.aracservisbakim;
}