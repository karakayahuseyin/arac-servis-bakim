package com.example.aracservisbakim.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class YeniKayitController {

    @FXML
    private TextField parca_adi_text;

    @FXML
    private TextField stok_text;

    @FXML
    private ComboBox model_combo;

    @FXML
    private TextField fiyat_text;


    private Stage stage;

    public YeniKayitController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void yeni_kayit(ActionEvent event) {
        String selectedValue = model_combo.getValue().toString();
        System.out.println("hello");
        VeritabaniKontrolör.insertYedekParca(parca_adi_text.getText(), Integer.parseInt(stok_text.getText())  ,Integer.parseInt(fiyat_text.getText()),Integer.parseInt(model_combo.getValue().toString()));
        System.out.println("Parça Başarıyla eklendi");
        if (stage != null) {
            stage.close();
        }
    }
    int sayac=0;
    @FXML
    protected void comboEvent() {
        if (sayac==0){
            ObservableList<Integer> faturaCesit = FXCollections.observableArrayList(VeritabaniKontrolör.loadCarModels());
            model_combo.getItems().addAll(faturaCesit);}
        //faturaComboya ilk kez tıkladığımız zaman alış ve satış değerlerini comboboxa atar sayac değişkeninin kullanılmasının sebebi
        //bu işlemin bir kez yapılması istenmesi kullanılmadığı zaman her müşteri seçiminde at alta sonsuza kadar alış satış değerlerini ekliyor
        sayac+=1;
    }

    @FXML
    void ekle_iptal(ActionEvent event) {

        if (stage != null) {
            stage.close();
        }
    }
}
