package com.example.aracservisbakim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Servis {

    @FXML
    private TextField arac_plaka_giris;

    @FXML
    private TextField ariza_giris;

    @FXML
    private TableColumn<?, ?> ariza_sutun;

    @FXML
    private Button ariza_tamir_ekle_buton;

    @FXML
    private TableView<?> arıza_tamir_tablo;

    @FXML
    private Button duzenle_buton;

    @FXML
    private Button ekle_buton;

    @FXML
    private TableColumn<?, ?> fiyat_sutun;

    @FXML
    private TextField iscilik_ucreti_giris;

    @FXML
    private TableColumn<?, ?> iscilik_ucreti_sutun;

    @FXML
    private TableColumn<?, ?> maliyet_sutun;

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
    private TableView<Stok> stok_tablo;

    @FXML
    private TextField tamir_suresi_giris;

    @FXML
    private TableColumn<?, ?> tamir_suresi_sutun;

    @FXML
    private TextField tarih_giris;

    @FXML
    private TableColumn<?, ?> uyumlu_arabalar_sutun;

    @FXML
    private TextField yedek_parca_giris;

    @FXML
    private TableColumn<?, ?> yedek_parca_sutun;

    @FXML
    void ariza_tamir_ekle_tiklandi(ActionEvent event) {

    }

    @FXML
    void duzenle_tiklandi(ActionEvent event) {
        // tabloda seçili satırı al
        Stok secilenStok = stok_tablo.getSelectionModel().getSelectedItem();

        if (secilenStok != null) {
            // stok düzenleme ekranını aç
            // stok düzenleme ekranına seçilen stokun bilgilerini gönder

        } else {
            System.out.println("Lütfen bir satır seçin.");
        }

    }

    @FXML
    void ekle_tiklandi(ActionEvent event) {

    }

    @FXML
    void sil_tiklandi(ActionEvent event) {

    }

    @FXML
    void rapor_islemleri_tiklandi(ActionEvent event) {
        rapor_islemleri_ekran.setVisible(true);
        stok_islemleri_ekran.setVisible(false);
    }

    @FXML
    void stok_islemleri_tiklandi(ActionEvent event) {
        rapor_islemleri_ekran.setVisible(false);
        stok_islemleri_ekran.setVisible(true);

        // Tabloyu doldur
    }

    public void initialize() throws IOException, ClassNotFoundException {
        rapor_islemleri_ekran.setVisible(false);
        stok_islemleri_ekran.setVisible(false);

    }


}
