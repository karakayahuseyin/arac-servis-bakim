package com.example.aracservisbakim.model;

public class ArizaTamirSatir {
    int yedekParcaId;
    String yedekParca;
    int tamirSuresi;
    int iscilikUcreti;
    int toplamUcret;

    public ArizaTamirSatir(int yedekParçaId, String yedekParca, int tamirSuresi, int iscilikUcreti, int toplamUcret) {
        this.yedekParcaId = yedekParçaId;
        this.yedekParca = yedekParca;
        this.tamirSuresi = tamirSuresi;
        this.iscilikUcreti = iscilikUcreti;
        this.toplamUcret = toplamUcret;
    }

    public int getYedekParcaId() {
        return yedekParcaId;
    }

    public String getYedekParca() {
        return yedekParca;
    }

    public int getTamirSuresi() {
        return tamirSuresi;
    }

    public int getIscilikUcreti() {
        return iscilikUcreti;
    }

    public int getToplamUcret() {
        return toplamUcret;
    }
}
