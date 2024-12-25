package com.example.aracservisbakim.model;

public class KullaniciModel {
    private final String ad;
    private final String soyad;
    private final String mail;
    private final String yas;
    private final String sifre;
    private final String kullaniciAdi;
    private int id;

    public KullaniciModel(String ad, String soyad, String mail, String yas, String sifre) {
        this.ad = ad;
        this.soyad = soyad;
        this.mail = mail;
        this.yas = yas;
        this.sifre = sifre;
        this.kullaniciAdi = this.ad.toLowerCase() + this.soyad.toLowerCase();
        this.id = 0;
    }


    public int getId() {
        return id;
    }

    public void setId(int newID) {
        this.id = newID;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getMail() {
        return mail;
    }

    public String getYas() {
        return yas;
    }

    public String getSifre() {
        return sifre;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }


}
