package com.example.eventfinder.modelli;

public class Eventi {
    public String urlImage;
    public String titolo;
    public String data;
    public String luogo;
    public String prezzo;

    public Eventi(String urlImage,String titolo, String data, String luogo, String prezzo) {
        this.urlImage = urlImage;
        this.titolo = titolo;
        this.data = data;
        this.luogo = luogo;
        this.prezzo = prezzo;
    }

    public String getUrlImage(){
        return urlImage;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getData() {
        return data;
    }

    public String getLuogo() {
        return luogo;
    }

    public String getPrezzo() {
        return prezzo;
    }
}
