package com.example.aracservisbakim;

public class Stok {
    private String parcaAdi;
    private double fiyat;
    private int stokMiktari;

    // Constructor
    public Stok(String parcaAdi, double fiyat, int stokMiktari) {
        this.parcaAdi = parcaAdi;
        this.fiyat = fiyat;
        this.stokMiktari = stokMiktari;
    }

    // Getters and Setters
    public String getParcaAdi() { return parcaAdi; }
    public double getFiyat() { return fiyat; }
    public int getStokMiktari() { return stokMiktari; }

    @Override
    public String toString() {
        return "Parça: " + parcaAdi + ", Fiyat: " + fiyat + ", Stok: " + stokMiktari;
    }
}
