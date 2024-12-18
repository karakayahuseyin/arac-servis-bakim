package com.example.aracservisbakim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Giris {

    @FXML
    private AnchorPane giris_ekran;

    @FXML
    private Button giris_ekrani_buton;

    @FXML
    private Label giris_hata_metni;

    @FXML
    private TextField giris_isim;

    @FXML
    private PasswordField giris_sifre;

    @FXML
    private Button giris_yap_buton;

    @FXML
    private TextField giriş_soyad;

    @FXML
    private TextField kayit_ad;

    @FXML
    private Button kayit_buton;

    @FXML
    private AnchorPane kayit_ekran;

    @FXML
    private Button kayit_ekrani_buton;

    @FXML
    private TextField kayit_eposta;

    @FXML
    private Label kayit_hata_metni;

    @FXML
    private PasswordField kayit_sifre;

    @FXML
    private TextField kayit_soyad;

    @FXML
    private TextField kayit_yas;

    @FXML
    private RadioButton teknik_servis_elemani_mi;

    @FXML
    void giris_ekrani_tiklandi(ActionEvent event) {
        kayit_ekran.setVisible(false);
        giris_ekran.setVisible(true);
    }

    @FXML
    void kayit_ekrani_tıklandi(ActionEvent event) {
        kayit_ekran.setVisible(true);
        giris_ekran.setVisible(false);
    }

    @FXML
    void giris_yap(ActionEvent event) throws IOException, ClassNotFoundException {
        // kullanıcı adı ve şifre kontrol et
        // eğer doğruysa oturum için gerekli bilgileri al
        int id = 1;
        String kullaniciAdi = "admin";
        boolean yetki = false;

        // Oturum oluştur
        Oturum oturum = Oturum.getOturum();
        oturum.oturumAc(id, kullaniciAdi, yetki);

        // Giriş ekranını kapat
        Stage stage = (Stage) giris_yap_buton.getScene().getWindow();
        stage.close();

        Stage newStage = new Stage();
        Parent root;

        // Yetkiye göre sayfa aç
        if (oturum.getYetki()) {
            root = FXMLLoader.load(getClass().getResource("servis.fxml"));
            newStage.setTitle("Servis İşlemleri");
        } else {
            root = FXMLLoader.load(getClass().getResource("musteri.fxml"));
            newStage.setTitle("Müşteri İşlemleri");
        }

        newStage.setScene(new Scene(root, 1080, 720));
        newStage.show();
    }

    @FXML
    void kayit(ActionEvent event) {
        // Kayıt işlemleri yapılacak ve veritabanına kaydedilecek
    }

    public void initialize() throws IOException, ClassNotFoundException {
        giris_ekran.setVisible(true);
        kayit_ekran.setVisible(false);
    }
}