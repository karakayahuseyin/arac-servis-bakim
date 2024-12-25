package com.example.aracservisbakim.model;

import java.util.Date;

public class TamirTabloSatir {
    private final String yedekParca;
    private final String tamirSüresi;
    private final String baslangicTarih;
    private final String marka;
    private final String modeli;
    private final String plaka;
    private final String maliyet;



    public TamirTabloSatir(String yedekParca, String tamirSüresi, String baslangicTarih, String marka, String modeli, String plaka, String maliyet) {
        this.yedekParca = yedekParca;
        this.tamirSüresi = tamirSüresi;
        this.baslangicTarih = baslangicTarih;
        this.marka = marka;
        this.modeli = modeli;
        this.plaka = plaka;
        this.maliyet = maliyet;
    }

    public String getYedekParca() {
        return yedekParca;
    }

    public String getTamirSüresi() {
        return tamirSüresi;
    }

    public String getBaslangicTarih() {
        return baslangicTarih;
    }

    public String getMarka() {
        return marka;
    }

    public String getModeli() {
        return modeli;
    }

    public String getPlaka() {
        return plaka;
    }

    public String getMaliyet() {
        return maliyet;
    }
}
