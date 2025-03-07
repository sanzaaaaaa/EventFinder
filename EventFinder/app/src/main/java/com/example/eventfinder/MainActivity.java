package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.login) {
                    startActivity(new Intent(MainActivity.this, Registrati.class));
                    return true;
                } else if (item.getItemId() == R.id.btnLogout) { // attenzione modificato provvisoriamente
                    startActivity(new Intent(MainActivity.this, Preferiti.class));
                    return true;
                } else if (item.getItemId() == R.id.profilo) {
                    startActivity(new Intent(MainActivity.this, Profilo.class));
                    return true;
                }
                return false;  // Default case
            }

        }); */
        
    }
}