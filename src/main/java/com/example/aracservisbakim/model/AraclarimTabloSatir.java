package com.example.aracservisbakim.model;

public class AraclarimTabloSatir {
    private final String durum;
    private final String markaModel;
    private final String plaka;
    private final String tamirSuresi;
    private final String periyodikBakimSuresi;

    public AraclarimTabloSatir(String markaModel, String plaka, String tamirSuresi, String periyodikBakimSuresi) {
        this.markaModel = markaModel;
        this.plaka = plaka;
        this.tamirSuresi = tamirSuresi;
        this.periyodikBakimSuresi = periyodikBakimSuresi;
        if (tamirSuresi == null) {
            this.durum = "Hazır";
        } else {
            this.durum = "Tamirde";
        }
    }

    public String getMarkaModel() {
        return markaModel;
    }

    public String getPlaka() {
        return plaka;
    }

    public String getTamirSuresi() {
        return tamirSuresi;
    }

    public String getPeriyodikBakimSuresi() {
        return periyodikBakimSuresi;
    }

    public String getDurum() {
        return durum;
    }
}
