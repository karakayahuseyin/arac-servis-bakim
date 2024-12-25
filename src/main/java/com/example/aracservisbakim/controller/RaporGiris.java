package com.example.aracservisbakim.controller;

import com.example.aracservisbakim.Oturum;
import com.example.aracservisbakim.model.ArizaTamirSatir;
import com.example.aracservisbakim.model.MuayeneTabloSatir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RaporGiris
{
    RaporGiris() {}

    static public void muayene_report (MuayeneTabloSatir muayeneBilgileri) {
        // ad soyad mail yaş password kullaniciadi
        String query;
        query = "INSERT INTO Muayene (Servis_Teknikeri, Tarih, Plaka) VALUES (?, ?, ?)";

        try (Connection conn = VeritabaniBaglantisi.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, Oturum.getOturum().getBilgiler().getId());
            pstmt.setString(2, muayeneBilgileri.getTarih());
            pstmt.setString(3, muayeneBilgileri.getPlaka());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("------------Muayene Raporu Eklendi------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public void tamir_report (ArizaTamirSatir tamirBilgileri, int muayene_id)
    {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // Sonucu yazdır
        System.out.println("Bugünün tarihi: " + formattedDate);
        String query;
        query = "INSERT INTO Tamir (Yedek_Parça, Tamir_Süresi, İşcilik_Ücreti, Toplam_ücret, Muayene_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = VeritabaniBaglantisi.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, tamirBilgileri.getYedekParcaId());
            pstmt.setInt(2, tamirBilgileri.getTamirSuresi());
            pstmt.setInt(3, tamirBilgileri.getIscilikUcreti());
            pstmt.setInt(4, tamirBilgileri.getToplamUcret());
            pstmt.setInt(5, muayene_id);


            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("------------Tamir Raporu Eklendi------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
