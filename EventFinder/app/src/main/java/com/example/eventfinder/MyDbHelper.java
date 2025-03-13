package com.example.eventfinder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Utenti";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Utenti";
    private static final String KEY_ID = "id";
    private static final String KEY_NOME = "nome";
    private static final String KEY_COGNOME = "cognome";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_DATA_DI_NASCITA = "data_di_nascita";
    private static final String KEY_PASSWORD = "password";



    public MyDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLE Utenti id INTEGER PRIMARY KEY AUTO INCREMENT, nome TEXT, cognome TEXT, email TEXT, data_di_nascita TEXT, password TEXT)
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NOME + " TEXT, " + KEY_COGNOME + " TEXT, " + KEY_EMAIL + " TEXT, " + KEY_DATA_DI_NASCITA + " TEXT, " + KEY_PASSWORD + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }
}
