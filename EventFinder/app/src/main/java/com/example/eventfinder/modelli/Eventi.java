package com.example.eventfinder.modelli;

public class Eventi {
    public String urlImage;
    public String titolo;
    public String data;
    public String luogo;

    public Eventi(String urlImage,String titolo, String data, String luogo) {
        this.urlImage = urlImage;
        this.titolo = titolo;
        this.data = data;
        this.luogo = luogo;
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
}
