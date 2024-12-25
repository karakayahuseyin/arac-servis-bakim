package com.example.aracservisbakim.controller;

import com.example.aracservisbakim.Oturum;
import com.example.aracservisbakim.model.AraclarimTabloSatir;
import com.example.aracservisbakim.model.MuayeneTabloSatir;
import com.example.aracservisbakim.model.TamirTabloSatir;
import com.example.aracservisbakim.model.YedekParcaTabloSatir;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeritabaniKontrolör {
    public VeritabaniKontrolör() {

    }

    public static ObservableList<TamirTabloSatir> getRepairsTable(String plaka, Oturum oturum) {
        String query2 = "SELECT YedekParca.Yedek_Parça_Adı AS Yedek_Parça, Tamir.Tamir_Süresi, Muayene.Tarih, ArabaModeli.Marka, ArabaModeli.Modeli AS Model, Muayene.Plaka, Tamir.Toplam_ücret FROM MüsteriAraba INNER JOIN Muayene ON MüsteriAraba.Plaka = Muayene.Plaka INNER JOIN Tamir ON Muayene.id = Tamir.Muayene_id INNER JOIN YedekParca ON Tamir.Yedek_Parça = YedekParca.id INNER JOIN ArabaModeli ON MüsteriAraba.Araba_Modeli = ArabaModeli.id WHERE Muayene.Plaka = ?";
        ObservableList<TamirTabloSatir> tablo = FXCollections.observableArrayList();

        try (Connection connection = VeritabaniBaglantisi.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query2)) {

            pstmt.setString(1, plaka); // Örneğin, MusteriID = 1

            // Sorguyu çalıştırma
            ResultSet resultSet = pstmt.executeQuery();

            // Verileri listeye ekleme
            while (resultSet.next()) {
                String yedekParça = resultSet.getString("Yedek_Parça");
                String tamirSuresi = resultSet.getString("Tamir_Süresi");
                String tarih = resultSet.getString("Tarih");
                String marka = resultSet.getString("Marka");
                String modeli = resultSet.getString("Model");
                // String plaka = resultSet.getString("Plaka");
                String toplamUcret = resultSet.getString("Toplam_ücret");

                TamirTabloSatir satir = new TamirTabloSatir(yedekParça, tamirSuresi, tarih, marka, modeli, plaka, toplamUcret);
                tablo.add(satir);
            }

            // Veritabanı bağlantısını kapatma
            resultSet.close();
            pstmt.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tablo;
    }

    public static ObservableList<MuayeneTabloSatir> getExaminationsTable(String plaka, Oturum oturum) {
        ObservableList<MuayeneTabloSatir> tablo = FXCollections.observableArrayList();
        String query1 = "SELECT Plaka, Tarih from Muayene WHERE Plaka = '" + plaka + "';";

        try (Connection conn = VeritabaniBaglantisi.getConnection()) {
            Statement statement1 = conn.createStatement();

            ResultSet resultSet1 = statement1.executeQuery(query1);

            while (resultSet1.next()) {
                MuayeneTabloSatir satir = new MuayeneTabloSatir(resultSet1.getString("plaka"), resultSet1.getString("tarih"));
                tablo.add(satir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tablo;
    }

    public static List<AraclarimTabloSatir> getMyCarsTable(int id, Oturum oturum) {
        List<AraclarimTabloSatir> araclar = new ArrayList<>();

        String sql = "SELECT ma.Plaka, am.Marka, am.Modeli, MAX(t.Tamir_Süresi) AS Tamir_Süresi, am.Periyodik_Bakım_Süresi " +
                "FROM MüsteriAraba ma " +
                "JOIN ArabaModeli am ON ma.Araba_Modeli = am.id " +
                "LEFT JOIN Muayene mu ON ma.Plaka = mu.Plaka " +
                "LEFT JOIN Tamir t ON mu.id = t.Muayene_id " +
                "WHERE ma.Ruhsat_Sahibi = ? " +
                "GROUP BY ma.Plaka, am.Marka, am.Modeli, am.Periyodik_Bakım_Süresi";

        try (Connection connection = VeritabaniBaglantisi.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, oturum.getBilgiler().getId()); // Örneğin, MusteriID = 1

            // Sorguyu çalıştırma
            ResultSet resultSet = pstmt.executeQuery();

            // Verileri listeye ekleme
            while (resultSet.next()) {
                String marka = resultSet.getString("Marka");
                String modeli = resultSet.getString("Modeli");
                String plaka = resultSet.getString("Plaka");
                String tamirSuresi = resultSet.getString("Tamir_Süresi");
                String periyodikBakimSüresi = resultSet.getString("Periyodik_Bakim_Süresi");



                AraclarimTabloSatir satir = new AraclarimTabloSatir(marka + " " + modeli, plaka, tamirSuresi, periyodikBakimSüresi);
                System.out.println(satir.getMarkaModel());
                araclar.add(satir);
                // System.out.println("----------------------------");
            }

            // Veritabanı bağlantısını kapatma
            resultSet.close();
            pstmt.close();
            connection.close();

            return araclar;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return araclar;
    }

    /*
    public static void printFromDatabase() {

        String query1 = "SELECT Modeli from ArabaModeli";

        try (Connection conn = VeritabaniBaglantisi.getConnection()) {
            Statement statement1 = conn.createStatement();

            ResultSet resultSet1 = statement1.executeQuery(query1);

            while (resultSet1.next()) {
                System.out.println("Marka: " + resultSet1.getString("Modeli"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */



    public static void insertYedekParca(String parcaAdi, int stok, int fiyat, int arabaModeli) {

        String query = "INSERT INTO YedekParca (Yedek_Parça_Adı, Stok, Fiyat, Araba_Modeli) VALUES (?, ?, ?, ?)";

        try (Connection connection = VeritabaniBaglantisi.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            // Parametreleri ata
            pstmt.setString(1, parcaAdi);
            pstmt.setInt(2, stok);
            pstmt.setInt(3, fiyat);
            pstmt.setInt(4, arabaModeli);

            // Sorguyu çalıştır
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Kayıt başarıyla eklendi!");
            }
        } catch (SQLException e) {
            System.err.println("Veri eklenirken hata oluştu: " + e.getMessage());
        }
    }
    public static List<Integer> loadCarModels() {
        List<Integer> carModels = new ArrayList<>();
        String query = "SELECT * FROM ArabaModeli";  // Arabamodeli tablosundan araba adı çekiyoruz

        try (Connection connection = VeritabaniBaglantisi.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            // Veritabanından arabalar alınıp listeye ekleniyor
            while (rs.next()) {
                int carModel = rs.getInt("id");
                carModels.add(carModel);  // Listeye araba ismi ekleniyor
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carModels;  // Araba isimlerini döndürüyoruz
    }
    public static ObservableList<YedekParcaTabloSatir> getStocksTable(Oturum oturum) {
        ObservableList<YedekParcaTabloSatir> tablo = FXCollections.observableArrayList();
        String query1 = "SELECT * from YedekParca ";

        try (Connection conn = VeritabaniBaglantisi.getConnection()) {
            Statement statement1 = conn.createStatement();

            ResultSet resultSet1 = statement1.executeQuery(query1);

            while (resultSet1.next()) {
                YedekParcaTabloSatir satir = new YedekParcaTabloSatir(resultSet1.getString("Yedek_Parça_Adı"),resultSet1.getInt("id"),resultSet1.getInt("Stok"),resultSet1.getInt("Fiyat"),resultSet1.getInt("Araba_Modeli"));
                tablo.add(satir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tablo;
    }

    public static void deleteYedekParca(int id) {
        String query = "DELETE FROM YedekParca WHERE id = ?";  // Belirli bir ID'ye sahip araba modelini sil

        try (Connection connection = VeritabaniBaglantisi.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            // Silme sorgusu için ID'yi parametre olarak ekle
            ps.setInt(1, id);

            // Sorguyu çalıştır
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("ID " + id + " olan Yedek Parca başarıyla silindi.");
            } else {
                System.out.println("ID " + id + " olan Yedek Parca bulunamadı.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void updateYedekParca(int id, String parcaAdi, int stok, int fiyat, int arabaModeli) {

        String query = "UPDATE YedekParca SET Yedek_Parça_Adı = ?, Stok = ?, Fiyat = ?, Araba_Modeli = ? WHERE id = ?";

        try (Connection connection = VeritabaniBaglantisi.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            // Parametreleri ata
            pstmt.setString(1, parcaAdi);
            pstmt.setInt(2, stok);
            pstmt.setInt(3, fiyat);
            pstmt.setInt(4, arabaModeli);
            pstmt.setInt(5, id);  // Güncellenmesi gereken kaydın ID'sini belirt

            // Sorguyu çalıştır
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Kayıt başarıyla güncellendi!");
            }
        } catch (SQLException e) {
            System.err.println("Veri güncellenirken hata oluştu: " + e.getMessage());
        }
    }



}
