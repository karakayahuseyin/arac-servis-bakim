package com.example.aracservisbakim.controller;

import java.sql.*;

public class VeritabaniBaglantisi {
    private static Connection connection;
    // Veritabanı bağlantı bilgileri
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ArabaServisi;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "Gccu9605_";
    // Singleton bağlantı elde etme
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Başarıyla veritabanına istek gönderildi ve alındı");
        }
        return connection;
    }
}