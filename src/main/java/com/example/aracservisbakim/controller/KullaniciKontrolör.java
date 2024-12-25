package com.example.aracservisbakim.controller;

import com.example.aracservisbakim.model.KullaniciModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KullaniciKontrolör {
    public KullaniciKontrolör() {}

    public static int login(String ad, String kullaniciAdi, String sifre) {
        String sql = "SELECT id, Kullanici_Adi, Password FROM Musteri WHERE Kullanici_Adi = ? AND Password = ?";
        int res = 0; // Eğer 0 ise sorunsuz giriş yapıldı, Eğer ki 1 ise hata oluştu.
        try (Connection conn = VeritabaniBaglantisi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Kullanıcı adı ve şifreyi sorguya bağla
            pstmt.setString(1, kullaniciAdi);
            pstmt.setString(2, sifre);

            // Sorguyu çalıştır ve sonucu al
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Kullanıcı bilgilerini al ve bir User nesnesi döndür
                int id = rs.getInt("id");
                // String role = rs.getString("role");
                System.out.println("Başarıyla giriş yaptınız");

                return res = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Kullanıcı bulunamazsa 1 döndür
        return res = 1;
    }

    public static int loginAsTechnician(String ad, String kullaniciAdi, String sifre) {
        String sql = "SELECT id, Kullanici_Adi, Password FROM ServisTeknikeri WHERE Kullanici_Adi = ? AND Password = ?";
        int res = 0; // Eğer 0 ise sorunsuz giriş yapıldı, Eğer ki 1 ise hata oluştu.
        try (Connection conn = VeritabaniBaglantisi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Kullanıcı adı ve şifreyi sorguya bağla
            pstmt.setString(1, kullaniciAdi);
            pstmt.setString(2, sifre);

            // Sorguyu çalıştır ve sonucu al
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Kullanıcı bilgilerini al ve bir User nesnesi döndür
                int id = rs.getInt("id");
                // String role = rs.getString("role");
                System.out.println("Başarıyla giriş yaptınız");

                return res = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Kullanıcı bulunamazsa 1 döndür
        return res = 1;
    }

    public static void register(boolean yetki, KullaniciModel kullaniciModelBilgileri) {
        // ad soyad mail yaş password kullaniciadi
        String query;
        if(yetki) {
            query = "INSERT INTO ServisTeknikeri (Ad, Soyad, Mail, Password, Kullanici_Adi, Yaş) VALUES (?, ?, ?, ?, ?, ?)";
        }
        else {
            query = "INSERT INTO Musteri (Ad, Soyad, Mail, Yaş, Password, Kullanici_Adi) VALUES (?, ?, ?, ?, ?, ?)";
        }

        System.out.println(kullaniciModelBilgileri.getMail());


        try (Connection conn = VeritabaniBaglantisi.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, kullaniciModelBilgileri.getAd());
            pstmt.setString(2, kullaniciModelBilgileri.getSoyad());
            pstmt.setString(3, kullaniciModelBilgileri.getMail());
            pstmt.setInt(yetki ? 6 : 4, Integer.parseInt(kullaniciModelBilgileri.getYas()));

            pstmt.setString(yetki ? 4 : 5, kullaniciModelBilgileri.getSifre());
            pstmt.setString(yetki ? 5 : 6, kullaniciModelBilgileri.getKullaniciAdi());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("---------------------------------------------");
                System.out.println("Kullanıcı adınız: " + kullaniciModelBilgileri.getKullaniciAdi() + ", Şifreniz: " + kullaniciModelBilgileri.getSifre());
                System.out.println("Kullanıcı eklendi!");
                System.out.println("---------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static KullaniciModel getUserData(String kullaniciAdi) {
        String query1 = "SELECT * from Musteri WHERE Kullanici_Adi = ?";

        try (Connection conn = VeritabaniBaglantisi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query1)) {

            // Kullanıcı adı parametresini sorguya bağla
            pstmt.setString(1, kullaniciAdi);

            // Sorguyu çalıştır ve sonucu al
            ResultSet rs = pstmt.executeQuery();

            // Eğer kullanıcı bulunduysa, verileri değişkenlere ata
            if (rs.next()) {
                int id = rs.getInt("id");
                String ad = rs.getString("Ad");
                String soyad = rs.getString("Soyad");
                String mail = rs.getString("Mail");
                kullaniciAdi = rs.getString("Kullanici_Adi");
                int yas = rs.getInt("Yaş");
                String password = rs.getString("Password");

                KullaniciModel oturumKullanicisi = new KullaniciModel(ad, soyad, mail, String.valueOf(yas), password);
                oturumKullanicisi.setId(id);

                return oturumKullanicisi;
            } else {
                String query2 = "SELECT * from ServisTeknikeri WHERE Kullanici_Adi = ?";

                try (Connection connection = VeritabaniBaglantisi.getConnection();
                     PreparedStatement pstmt2 = connection.prepareStatement(query2)) {

                    // Kullanıcı adı parametresini sorguya bağla
                    pstmt2.setString(1, kullaniciAdi);

                    // Sorguyu çalıştır ve sonucu al
                    ResultSet rs2 = pstmt2.executeQuery();

                    // Eğer kullanıcı bulunduysa, verileri değişkenlere ata
                    if (rs2.next()) {
                        int id = rs2.getInt("id");
                        String ad = rs2.getString("Ad");
                        String soyad = rs2.getString("Soyad");
                        String mail = rs2.getString("Mail");
                        kullaniciAdi = rs2.getString("Kullanici_Adi");
                        int yas = rs2.getInt("Yaş");
                        String password = rs2.getString("Password");

                        KullaniciModel oturumKullanicisi = new KullaniciModel(ad, soyad, mail, String.valueOf(yas), password);
                        oturumKullanicisi.setId(id);

                        return oturumKullanicisi;
                    } else {
                        System.out.println("Kullanıcı bulunamadı.");

                        return null;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
