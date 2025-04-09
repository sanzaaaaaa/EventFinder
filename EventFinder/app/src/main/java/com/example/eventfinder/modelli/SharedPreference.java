package com.example.eventfinder.modelli;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_ID = "utente_id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NOME = "nome";
    private static final String KEY_COGNOME = "cognome";
    private static final String KEY_DATA_NASCITA = "data_di_nascita";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;


    public SharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLoggedIn(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public boolean isLoggedIn() {

        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void saveId(int id) {
        editor.putInt(KEY_ID, id);
        editor.apply();
    }

    public int getId() {
        return sharedPreferences.getInt("utente_id", 1);
    }

    public void saveEmail(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public String getEmail() {
        return sharedPreferences.getString(KEY_EMAIL, null);
    }

    public void saveNome(String nome) {
        editor.putString(KEY_NOME, nome);
        editor.apply();
    }

    public String getNome() {
        return sharedPreferences.getString(KEY_NOME, "Nome non disponibile");
    }


    public void saveCognome(String cognome) {
        editor.putString(KEY_COGNOME, cognome);
        editor.apply();
    }

    public String getCognome() {
        return sharedPreferences.getString(KEY_COGNOME, "Cognome non disponibile");
    }

    public void saveDataDiNascita(String dataDiNascita) {
        editor.putString(KEY_DATA_NASCITA, dataDiNascita);
        editor.apply();
    }

    public String getDataDiNascita() {
        return sharedPreferences.getString(KEY_DATA_NASCITA, "Data di nascita non disponibile");
    }

}