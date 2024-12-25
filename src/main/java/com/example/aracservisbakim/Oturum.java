package com.example.aracservisbakim;

import com.example.aracservisbakim.controller.KullaniciKontrolör;
import com.example.aracservisbakim.model.KullaniciModel;

public class Oturum {
    // Singleton tasarım deseni

    private static Oturum oturum = null;
    private int id;
    private String kullaniciAdi;
    private boolean yetki;
    private KullaniciModel bilgiler;

    private Oturum() {
        this.id = 0;
        this.kullaniciAdi = "";
        this.yetki = false;
        this.bilgiler = null;
    }

    public static Oturum getOturum() {
        if (oturum == null) {
            oturum = new Oturum();
        }
        return oturum;
    }

    public void oturumAc(int id, String kullaniciAdi, boolean yetki)
    {
        if (oturum == null) {
            oturum = new Oturum();
        }
        if (this.id != 0) {
            System.out.println("Zaten bir oturum açık.");
        }
        this.id = id;
        this.kullaniciAdi = kullaniciAdi;
        this.yetki = yetki;
        this.bilgiler = KullaniciKontrolör.getUserData(this.kullaniciAdi);
    }

    public void oturumKapat() {
        this.id = 0;
        this.kullaniciAdi = "";
        this.yetki = false;
        this.bilgiler = null;
    }

    public int getId() {
        return id;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public boolean getYetki() {
        return yetki;
    }

    public KullaniciModel getBilgiler() { return bilgiler; }
}
