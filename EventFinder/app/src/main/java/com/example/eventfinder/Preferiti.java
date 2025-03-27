package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Preferiti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferiti);

        ImageButton btnBiglietto = findViewById(R.id.btnBiglietti2);
        ImageButton btnPreferiti = findViewById(R.id.btnPrefe2);
        ImageButton btnHome = findViewById(R.id.btnHome2);
        ImageButton btnAmici = findViewById(R.id.btnHomeAmici2);
        ImageButton btnProfilo = findViewById(R.id.btnProfilo2);

        btnBiglietto.setOnClickListener(v -> {
            Intent biglietto = new Intent(Preferiti.this, Biglietti.class);
            startActivity(biglietto);
        });

        btnPreferiti.setOnClickListener(v -> {
            Intent preferiti = new Intent(Preferiti.this, Preferiti.class);
            startActivity(preferiti);

        });

        btnHome.setOnClickListener(v -> {
            Intent home = new Intent(Preferiti.this, HomeActivity.class);
            startActivity(home);
        });

        btnAmici.setOnClickListener(v -> {
            Intent amici = new Intent(Preferiti.this, Amici.class);
            startActivity(amici);
        });

        btnProfilo.setOnClickListener(v -> {
            Intent profilo = new Intent(Preferiti.this, Profilo.class);
            startActivity(profilo);
        });
    }
}