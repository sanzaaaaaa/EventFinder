package com.example.eventfinder.modelli;

public class utenti {
    private String nome;
    private String cognome;
    private String email;
    private String data_di_nascita;
    private String password;

    public utenti(String nome, String cognome, String email, String data_di_nascita, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.data_di_nascita = data_di_nascita;
        this.password = password;
    }
}
