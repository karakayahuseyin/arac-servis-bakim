package com.example.aracservisbakim.model;

public class YedekParcaTabloSatir {


    public YedekParcaTabloSatir(String parcaAdi,int parcaId,int stokMiktari, int fiyat,   int uyumluArabaModeli) {
        this.parcaAdi = parcaAdi;
        this.fiyat = fiyat;
        this.stokMiktari = stokMiktari;
        this.parcaId = parcaId;
        this.uyumluArabaModeli = uyumluArabaModeli;
    }

    private String parcaAdi;
    private int fiyat;
    private int stokMiktari;
    private int parcaId;
    private  int uyumluArabaModeli;

    public String getParcaAdi() {
        return parcaAdi;
    }

    public void setParcaAdi(String parcaAdi) {
        this.parcaAdi = parcaAdi;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }

    public int getStokMiktari() {
        return stokMiktari;
    }

    public void setStokMiktari(int stokMiktari) {
        this.stokMiktari = stokMiktari;
    }

    public int getParcaId() {
        return parcaId;
    }

    public void setParcaId(int parcaId) {
        this.parcaId = parcaId;
    }

    public int getUyumluArabaModeli() {
        return uyumluArabaModeli;
    }

    public void setUyumluArabaModeli(int arabaModeli) {
        this.uyumluArabaModeli = arabaModeli;
    }
}
