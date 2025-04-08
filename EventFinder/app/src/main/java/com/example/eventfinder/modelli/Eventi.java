package com.example.eventfinder.modelli;

import com.google.gson.annotations.SerializedName;

public class Eventi {
    @SerializedName("id")
    private int id;
    @SerializedName("urlimage")
    private String urlImage;

    @SerializedName("titolo")
    private String titolo;

    @SerializedName("data_evento")
    private String data;

    @SerializedName("luogo")
    private String luogo;

    @SerializedName("info_evento")
    private String info_evento;

    @SerializedName("info_artista")
    private String info_artista;

    @SerializedName("prezzo")
    private String prezzo;

    public Eventi(int id, String urlImage, String titolo, String data, String luogo, String info_evento, String info_artista, String prezzo) {
        this.id = id;
        this.urlImage = urlImage;
        this.titolo = titolo;
        this.data = data;
        this.luogo = luogo;
        this.info_evento = info_evento;
        this.info_artista = info_artista;
        this.prezzo = prezzo;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getInfo_evento() {
        return info_evento;
    }

    public void setInfo_evento(String info_evento) {
        this.info_evento = info_evento;
    }

    public String getInfo_artista() {
        return info_artista;
    }

    public void setInfo_artista(String info_artista) {
        this.info_artista = info_artista;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }
}