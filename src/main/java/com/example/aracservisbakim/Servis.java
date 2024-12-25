package com.example.aracservisbakim;

import com.example.aracservisbakim.controller.*;
import com.example.aracservisbakim.model.ArizaTamirSatir;
import com.example.aracservisbakim.model.MuayeneTabloSatir;
import com.example.aracservisbakim.model.YedekParcaTabloSatir;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Servis {

    @FXML
    private TextField arac_plaka_giris;

    @FXML
    private Button ariza_tamir_ekle_buton;

    @FXML
    private TableView<ArizaTamirSatir> ariza_tamir_tablo;

    @FXML
    private Button duzenle_buton;

    @FXML
    private Button ekle_buton;

    @FXML
    private TableColumn<?, ?> fiyat_sutun;

    @FXML
    private TextField iscilik_ucreti_giris;

    @FXML
    private TableColumn<?, ?> parca_adi_sutun;

    @FXML
    private TableColumn<?, ?> parca_id_sutun;

    @FXML
    private Button rapor_islemleri;

    @FXML
    private AnchorPane rapor_islemleri_ekran;

    @FXML
    private Button sil_buton;

    @FXML
    private Button stok_islemleri_buton;

    @FXML
    private AnchorPane stok_islemleri_ekran;

    @FXML
    private TableColumn<?, ?> stok_sutun;

    @FXML
    private TableView<YedekParcaTabloSatir> stok_tablo;

    @FXML
    private TextField tamir_suresi_giris;

    @FXML
    private TextField tarih_giris;

    @FXML
    private TableColumn<?, ?> uyumlu_arabalar_sutun;

    @FXML
    private ComboBox<String> yedek_parca_giris;

    @FXML
    private Button kaydet_buton;

    @FXML
    private TableColumn<ArizaTamirSatir, Integer> yedek_parca_sutun;
    @FXML
    private TableColumn<ArizaTamirSatir, Integer> tamir_suresi_sutun;
    @FXML
    private TableColumn<ArizaTamirSatir, Integer> iscilik_ucreti_sutun;
    @FXML
    private TableColumn<ArizaTamirSatir, Integer> maliyet_sutun;

    private ObservableList<ArizaTamirSatir> arizaTamirListesi = FXCollections.observableArrayList();

    void ArizaTamirTablosunuAyarla() {
        yedek_parca_sutun.setCellValueFactory(new PropertyValueFactory<>("yedekParca"));
        tamir_suresi_sutun.setCellValueFactory(new PropertyValueFactory<>("tamirSuresi"));
        iscilik_ucreti_sutun.setCellValueFactory(new PropertyValueFactory<>("iscilikUcreti"));
        maliyet_sutun.setCellValueFactory(new PropertyValueFactory<>("toplamUcret"));

        ariza_tamir_tablo.setItems(arizaTamirListesi);
    }

    @FXML
    void rapor_kaydet(ActionEvent event)
    {
        // Rapor bilgilerini al ve RaporGiris classından raport() fonksiyonuna ver.
        String plaka = arac_plaka_giris.getText();
        String tarih = tarih_giris.getText();
        MuayeneTabloSatir muayeneBilgileri = new MuayeneTabloSatir(plaka, tarih);
        if (ariza_tamir_tablo.getItems().isEmpty()) {
            RaporGiris.muayene_report(muayeneBilgileri);
        } else {
            RaporGiris.muayene_report(muayeneBilgileri);

            String query = "SELECT id FROM Muayene WHERE Tarih = ? AND Plaka = ?";
            int id = 0;
            try (Connection conn = VeritabaniBaglantisi.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, tarih); // tarih değişkeni doğru mu kontrol edin
                pstmt.setString(2, plaka); // plaka değişkeni doğru mu kontrol edin
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    id = rs.getInt("id");
                    System.out.println("Muayene ID: " + id);
                } else {
                    System.out.println("Muayene kaydı bulunamadı. Yabancı anahtar hatası oluşabilir.");
                    return; // Muayene kaydı yoksa işlemi sonlandır
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }

            for (ArizaTamirSatir arizaTamir : arizaTamirListesi) {
                RaporGiris.tamir_report(arizaTamir, id);
            }
        }
    }

    @FXML
    void ariza_tamir_ekle_tiklandi(ActionEvent event) {
        // Kullanıcı seçimlerini kontrol et
        String selectedItem = yedek_parca_giris.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            System.out.println("Lütfen bir yedek parça seçiniz.");
            return;
        }

        String iscilikText = iscilik_ucreti_giris.getText();
        String tamirSuresiText = tamir_suresi_giris.getText();
        if (iscilikText.isEmpty() || tamirSuresiText.isEmpty()) {
            System.out.println("Lütfen gerekli alanları doldurunuz.");
            return;
        }

        // Yedek parça id ve fiyatı aynı sorgu ile al
        String query = "SELECT id, Fiyat FROM YedekParca WHERE Yedek_Parça_Adı = ?";
        int yedekParcaId = 0, yedekParcaFiyat = 0;
        try (Connection conn = VeritabaniBaglantisi.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, selectedItem);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                yedekParcaId = rs.getInt("id");
                yedekParcaFiyat = rs.getInt("Fiyat");
                System.out.println("Yedek Parça id: " + yedekParcaId + ", fiyat: " + yedekParcaFiyat);
            } else {
                System.out.println("Yedek parça bulunamadı.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Maliyet hesaplama
        int iscilikUcreti = 0, tamirSuresi = 0, maliyet = 0;
        try {
            iscilikUcreti = Integer.parseInt(iscilikText);
            tamirSuresi = Integer.parseInt(tamirSuresiText);
            maliyet = iscilikUcreti + yedekParcaFiyat;
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz giriş. Lütfen sayısal bir değer girin.");
            return;
        }

        // Yeni satır oluştur ve tabloya ekle
        ArizaTamirSatir arizaTamirSatir = new ArizaTamirSatir(yedekParcaId, yedek_parca_giris.getSelectionModel().getSelectedItem(), tamirSuresi, iscilikUcreti, maliyet);
        arizaTamirListesi.add(arizaTamirSatir);

        // Alanları temizle
        yedek_parca_giris.getSelectionModel().clearSelection();
        iscilik_ucreti_giris.clear();
        tamir_suresi_giris.clear();
    }

    @FXML
    void duzenle_tiklandi(ActionEvent event) throws IOException {
        // tabloda seçili satırı al
        YedekParcaTabloSatir secilenStok = stok_tablo.getSelectionModel().getSelectedItem();

        if (secilenStok != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("parca_duzenle.fxml"));

            // Yeni controller'ı al
            Stage stage = new Stage();
            stage.setTitle("Yeni Sayfa");
            // Yeni FXML dosyasını yükle
            StokKayitDuzenle controller = new StokKayitDuzenle(secilenStok,stage);

            loader.setController(controller);
            Parent root = loader.load();

            // Yeni bir pencere oluştur

            stage.setScene(new Scene(root));
            stage.show();

        } else {
            System.out.println("Lütfen bir satır seçin.");
        }

    }

    @FXML
    void ekle_tiklandi(ActionEvent event) {

        try {
            // Yeni FXML dosyasını yükle
            FXMLLoader loader = new FXMLLoader(getClass().getResource("parca_ekle.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Yeni Sayfa");
            YeniKayitController controller = new YeniKayitController(stage);


            loader.setController(controller);
            Parent root = loader.load();
            // Yeni bir pencere oluştur

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void sil_tiklandi(ActionEvent event) {
        YedekParcaTabloSatir selectedItem = stok_tablo.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // 1. Veritabanından sil

            int id=selectedItem.getParcaId();
            VeritabaniKontrolör.deleteYedekParca(id);
        }
        stok_tablo.getItems().remove(selectedItem);
    }

    @FXML
    void rapor_islemleri_tiklandi(ActionEvent event) {
        rapor_islemleri_ekran.setVisible(true);
        stok_islemleri_ekran.setVisible(false);
        //yedek parça giriş için comboboxa yedek parça adlarını ekle
        ObservableList<String> yedekParcaAdlari = FXCollections.observableArrayList();
        String query = "SELECT Yedek_Parça_Adı FROM YedekParca";
        try (Connection conn = VeritabaniBaglantisi.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                yedekParcaAdlari.add(rs.getString("Yedek_Parça_Adı"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        yedek_parca_giris.setItems(yedekParcaAdlari);
        // Tabloyu doldur
    }

    @FXML
    void stok_islemleri_tiklandi(ActionEvent event) {
        rapor_islemleri_ekran.setVisible(false);
        stok_islemleri_ekran.setVisible(true);
        Oturum oturum=Oturum.getOturum();
        StokTablosunuAyarla();
        stok_tablo.setItems(VeritabaniKontrolör.getStocksTable(oturum));

        // Tabloyu doldur
    }

    @FXML
    void stok_Goruntule(ActionEvent event) {

    }

    void StokTablosunuAyarla() { // Değişkenlerin eşlenmesi için
        parca_id_sutun.setCellValueFactory(new PropertyValueFactory<>("parcaId"));
        parca_adi_sutun.setCellValueFactory(new PropertyValueFactory<>("parcaAdi"));
        fiyat_sutun.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
        stok_sutun.setCellValueFactory(new PropertyValueFactory<>("stokMiktari"));
        uyumlu_arabalar_sutun.setCellValueFactory(new PropertyValueFactory<>("uyumluArabaModeli"));

    }

    public void initialize() throws IOException, ClassNotFoundException {
        Oturum oturum = Oturum.getOturum();
        rapor_islemleri_ekran.setVisible(false);
        stok_islemleri_ekran.setVisible(false);

        System.out.println(oturum.getKullaniciAdi());
        ArizaTamirTablosunuAyarla();
    }


}
