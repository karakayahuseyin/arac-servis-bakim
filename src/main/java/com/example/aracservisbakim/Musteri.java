package com.example.aracservisbakim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class Musteri {

    @FXML
    private AnchorPane arac_detay_ekran;

    @FXML
    private Button arac_detay_buton;

    @FXML
    private TableView<?> araclar_tablo;

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
    private TableView<?> muayene_gecmisi_tablo;

    @FXML
    private TableColumn<?, ?> plaka_sutun;

    @FXML
    private TableColumn<?, ?> sonraki_periyodik_bakim_sutun;

    @FXML
    private TableColumn<?, ?> sure_sutun;

    @FXML
    private TableView<?> tamir_gecmisi_tablo;

    @FXML
    private TableColumn<?, ?> tarih_sutun;

    @FXML
    void arac_detay_tiklandi(ActionEvent event) {
        arac_detay_ekran.setVisible(true);
        // muayene_gecmisi_tablo ve tamir_gecmisi_tablo tablolarını doldur.
    }

    public void initialize() {
        arac_detay_ekran.setVisible(false);
        Oturum oturum = Oturum.getOturum();
        // oturum sahibi id'si ile araçları getir ve araclar_tablo tablosunu doldur.
    }

}
