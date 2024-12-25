package com.example.aracservisbakim;

import com.example.aracservisbakim.controller.VeritabaniKontrolör;
import com.example.aracservisbakim.model.AraclarimTabloSatir;
import com.example.aracservisbakim.model.MuayeneTabloSatir;
import com.example.aracservisbakim.model.TamirTabloSatir;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class Musteri {

    @FXML
    private AnchorPane arac_detay_ekran;

    @FXML
    private Button arac_detay_buton;

    @FXML
    private TableView<AraclarimTabloSatir> araclar_tablo;

    @FXML
    private TableColumn<?, ?> ariza_sutun;

    @FXML
    private TableColumn<?, ?> ariza_tamir_durumu_sutun;

    @FXML
    private TableColumn<?, ?> baslangic_tarihi_sutun;

    @FXML
    private TableColumn<?, ?> durum_sutun;

    @FXML
    private TableColumn<?, ?> maliyet_sutun;

    @FXML
    private TableColumn<?, ?> marka_model_sutun;

    @FXML
    private TableView<MuayeneTabloSatir> muayene_gecmisi_tablo;

    @FXML
    private TableColumn<?, ?> plaka_sutun;

    @FXML
    private TableColumn<?, ?> sonraki_periyodik_bakim_sutun;

    @FXML
    private TableColumn<?, ?> sure_sutun;

    @FXML
    private TableColumn<?, ?> tarih_sutun;


    // Tamir Geçmişi Tablosu
    @FXML
    private TableView<TamirTabloSatir> tamir_gecmisi_tablo;

    @FXML
    private TableColumn<?, ?> tamir_ariza_sutun;

    @FXML
    private TableColumn<?, ?> tamir_sure_sutun;

    @FXML
    private TableColumn<?, ?> tamir_tarih_sutun;

    @FXML
    private TableColumn<?, ?> tamir_marka_sutun;

    @FXML
    private TableColumn<?, ?> tamir_model_sutun;

    @FXML
    private TableColumn<?, ?> tamir_plaka_sutun;

    @FXML
    private TableColumn<?, ?> tamir_maliyet_sutun;

    public Oturum oturum;

    void muayeneTablosunuAyarla() { // Değişkenlerin eşlenmesi için
        tarih_sutun.setCellValueFactory(new PropertyValueFactory<>("tarih"));
        ariza_tamir_durumu_sutun.setCellValueFactory(new PropertyValueFactory<>("plaka"));
    }

    void tamirTablosunuAyarla() { // Değişkenlerin eşlenmesi için
        tamir_ariza_sutun.setCellValueFactory(new PropertyValueFactory<>("yedekParca"));
        tamir_maliyet_sutun.setCellValueFactory(new PropertyValueFactory<>("maliyet"));
        tamir_marka_sutun.setCellValueFactory(new PropertyValueFactory<>("marka"));
        tamir_model_sutun.setCellValueFactory(new PropertyValueFactory<>("modeli"));
        tamir_plaka_sutun.setCellValueFactory(new PropertyValueFactory<>("plaka"));
        tamir_sure_sutun.setCellValueFactory(new PropertyValueFactory<>("tamirSüresi"));
        tamir_tarih_sutun.setCellValueFactory(new PropertyValueFactory<>("baslangicTarih"));
    }

    void araclarTablosunuAyarla() { // Değişkenlerin eşlenmesi için
        marka_model_sutun.setCellValueFactory(new PropertyValueFactory<>("markaModel"));
        durum_sutun.setCellValueFactory(new PropertyValueFactory<>("durum"));
        plaka_sutun.setCellValueFactory(new PropertyValueFactory<>("plaka"));
        sonraki_periyodik_bakim_sutun.setCellValueFactory(new PropertyValueFactory<>("periyodikBakimSuresi"));
    }

    @FXML
    void arac_detay_tiklandi(ActionEvent event) {
        oturum = Oturum.getOturum();
        arac_detay_ekran.setVisible(true);

        // Tablo üzerinde seçilmiş satır
        AraclarimTabloSatir selectedRow = araclar_tablo.getSelectionModel().getSelectedItem();

        // Muayene geçmiş tablosu ve Tamir geçmiş tablosunun "cellValueFactory" değerlerini ayarla
        muayeneTablosunuAyarla();
        tamirTablosunuAyarla();

        // Muayene geçmiş tablosu ve Tamir geçmiş tablosunun içeriğini veritabanından çek
        muayene_gecmisi_tablo.setItems(VeritabaniKontrolör.getExaminationsTable(selectedRow.getPlaka(), oturum));
        tamir_gecmisi_tablo.setItems(VeritabaniKontrolör.getRepairsTable(selectedRow.getPlaka(), oturum));
    }

    public void initialize() {
        Oturum oturum = Oturum.getOturum();

        arac_detay_ekran.setVisible(false);

        // Araçlar Tablosu - Oturum ID'si ile
        araclarTablosunuAyarla(); // Tablonun "cellValueFactory" değerlerini ayarla

        List<AraclarimTabloSatir> araclarimTabloList = VeritabaniKontrolör.getMyCarsTable(oturum.getId(), oturum); // OturumID'si ile tablo verilerini çek
        ObservableList<AraclarimTabloSatir> araclarimTablo = FXCollections.observableArrayList(); // Dinamik liste oluştur

        araclarimTablo.addAll(araclarimTabloList); // Listenin tüm elemanlarını observableList'e ekle
        araclar_tablo.setItems(araclarimTablo); // Tablonun itemlerini set et.
    }

}
