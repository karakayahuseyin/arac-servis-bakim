package com.example.aracservisbakim.model;

public class MuayeneTabloSatir {
    private final String plaka;
    private final String tarih;

    public MuayeneTabloSatir(String plaka, String tarih) {
        this.plaka = plaka;
        this.tarih = tarih;
    }

    public String getPlaka() {
        return plaka;
    }

    public String getTarih() {
        return tarih;
    }
}
