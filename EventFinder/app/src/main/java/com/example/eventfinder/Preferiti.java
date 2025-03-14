package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventfinder.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Preferiti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferiti);


        ListView preferiti = findViewById(R.id.listaPreferiti);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    startActivity(new Intent(Preferiti.this, MainActivity.class));
                    return true;
                } else if (item.getItemId() == R.id.preferiti) { // attenzione modificato provvisoriamente
                    startActivity(new Intent(Preferiti.this, Preferiti.class));
                    return true;
                } else if (item.getItemId() == R.id.biglietti) {
                    startActivity(new Intent(Preferiti.this, Biglietti.class));
                    return true;
                } else if (item.getItemId() == R.id.profilo) {
                    startActivity(new Intent(Preferiti.this, Profilo.class));
                    return true;
                }
                return false;
            }
        });

    }
}