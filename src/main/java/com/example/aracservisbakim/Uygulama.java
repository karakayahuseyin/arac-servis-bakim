package com.example.aracservisbakim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class Uygulama extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("giris.fxml"));
        stage.setTitle("Giriş Yap");
        stage.setScene(new Scene(root, 340, 400));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}