package com.example.aracservisbakim.controller;

import com.example.aracservisbakim.model.YedekParcaTabloSatir;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class StokKayitDuzenle {

    @FXML
    private TextField parca_adi_duzenle_text;
    @FXML
    private TextField stok_duzenle_text;
    @FXML
    private ComboBox model_duzenle_combo;
@FXML
    private TextField fiyat_duzenle_text;



    private YedekParcaTabloSatir selectedProduct;
    private Stage stage;

    public StokKayitDuzenle(YedekParcaTabloSatir selectedProduct, Stage stage) {
        this.selectedProduct = selectedProduct;
        this.stage=stage;
    }
    @FXML
    public void initialize() {
        // Seçilen ürünü kullanarak ilgili alanları doldur
        if (selectedProduct != null) {
            parca_adi_duzenle_text.setText(selectedProduct.getParcaAdi());
            stok_duzenle_text.setText(String.valueOf(selectedProduct.getStokMiktari()));
            model_duzenle_combo.setValue(selectedProduct.getUyumluArabaModeli());
            fiyat_duzenle_text.setText(String.valueOf(selectedProduct.getFiyat()));

        }
    }

    int sayac=0;
    @FXML
    protected void duzenleComboEvent() {


        if (sayac==0){
            ObservableList<Integer> faturaCesit = FXCollections.observableArrayList(VeritabaniKontrolör.loadCarModels());
            model_duzenle_combo.getItems().addAll(faturaCesit);}
        //faturaComboya ilk kez tıkladığımız zaman alış ve satış değerlerini comboboxa atar sayac değişkeninin kullanılmasının sebebi
        //bu işlemin bir kez yapılması istenmesi kullanılmadığı zaman her müşteri seçiminde at alta sonsuza kadar alış satış değerlerini ekliyor
        sayac+=1;
    }


    @FXML
    private void duzenleButtonClicked(ActionEvent event) {
        // Kullanıcıdan güncellenmiş verileri al
        String parcaAdi = parca_adi_duzenle_text.getText();
        int stok = Integer.parseInt(stok_duzenle_text.getText());
        int fiyat = Integer.parseInt(fiyat_duzenle_text.getText());
        int arabaModeli =Integer.parseInt(model_duzenle_combo.getValue().toString()) ;

        // Güncellenmiş veriyi veritabanına kaydet
        int id = selectedProduct.getParcaId() ;// Seçilen stok bilgisinden ID'yi al
        VeritabaniKontrolör.updateYedekParca(id, parcaAdi, stok, fiyat, arabaModeli);

        if (stage != null) {
            stage.close();
        }    }



    @FXML
    void duzenle_iptal(ActionEvent event) {

        if (stage != null) {
            stage.close();
        }

    }



}
