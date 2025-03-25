package com.example.eventfinder.modelli;

import com.google.gson.annotations.SerializedName;

public class Utente {
    @SerializedName("id")
    private int id;
    @SerializedName("nome")
    private String nome;
    @SerializedName("cognome")
    private String cognome;
    @SerializedName("email")
    private String email;
    @SerializedName("data_di_nascita")
    private String data_di_nascita;
    @SerializedName("password")
    private String password;

    public Utente(String nome, String cognome, String email, String data_di_nascita, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.data_di_nascita = data_di_nascita;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getData_di_nascita() {
        return data_di_nascita;
    }

    public void setData_di_nascita(String data_di_nascita) {
        this.data_di_nascita = data_di_nascita;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
