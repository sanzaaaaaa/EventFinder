package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Amici extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amici);

        ImageButton back = findViewById(R.id.btnBack);
        ImageButton btnBiglietto = findViewById(R.id.btnBiglietti3);
        ImageButton btnPreferiti = findViewById(R.id.btnPrefe3);
        ImageButton btnHome = findViewById(R.id.btnHome3);
        ImageButton btnAmici = findViewById(R.id.btnHomeAmici3);
        ImageButton btnProfilo = findViewById(R.id.btnProfilo3);

        btnBiglietto.setOnClickListener(v -> {
            Intent biglietto = new Intent(Amici.this, Biglietti.class);
            startActivity(biglietto);
        });

        btnPreferiti.setOnClickListener(v -> {
            Intent preferiti = new Intent(Amici.this, Preferiti.class);
            startActivity(preferiti);

        });

        btnHome.setOnClickListener(v -> {
            Intent home = new Intent(Amici.this, HomeActivity.class);
            startActivity(home);
        });

        btnAmici.setOnClickListener(v -> {
            Intent amici = new Intent(Amici.this, Amici.class);
            startActivity(amici);
        });

        btnProfilo.setOnClickListener(v -> {
            Intent profilo = new Intent(Amici.this, Profilo.class);
            startActivity(profilo);
        });

        back.setOnClickListener(v -> {
            Intent dietro = new Intent(Amici.this, HomeActivity.class);
            startActivity(dietro);
        });
    }
}