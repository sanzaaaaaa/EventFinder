package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.stream.DoubleStream;

public class Profilo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        Button sezAmici = findViewById(R.id.btnAmici);
        Button log = findViewById(R.id.btnLogout);


        ImageView profilo = findViewById(R.id.imgProfilo);

        // Usa Glide per caricare la GIF
        Glide.with(this)
                .asGif() // Specifica che vuoi caricare una GIF
                .load(R.drawable.iconautente) // Inserisci il nome della tua GIF nella cartella drawable
                .into(profilo); // Imposta la GIF nell'ImageView

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    startActivity(new Intent(Profilo.this, MainActivity.class));
                    return true;
                } else if (item.getItemId() == R.id.preferiti) { // attenzione modificato provvisoriamente
                    startActivity(new Intent(Profilo.this, Preferiti.class));
                    return true;
                } else if (item.getItemId() == R.id.biglietti) {
                    startActivity(new Intent(Profilo.this, Biglietti.class));
                    return true;
                } else if (item.getItemId() == R.id.profilo) {
                    startActivity(new Intent(Profilo.this, Profilo.class));
                    return true;
                }
                return false;
            }
        });
    }
}



