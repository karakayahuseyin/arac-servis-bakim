package com.example.aracservisbakim;

public class Oturum {
    // Singleton tasarım deseni

    private static Oturum oturum = null;
    private int id;
    private String kullaniciAdi;
    private boolean yetki;

    private Oturum() {
        this.id = 0;
        this.kullaniciAdi = "";
        this.yetki = false;
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
    }

    public void oturumKapat() {
        this.id = 0;
        this.kullaniciAdi = "";
        this.yetki = false;
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
}
