package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    //array di eventi
    String eventiList[] = {"evento 1", "evento 2", "evento 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        Button loginButtonHome = findViewById(R.id.loginHomeBtn);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.oggetti_listview, R.id.oggettiListView, eventiList);
        listView.setAdapter(arrayAdapter);

        loginButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.login) {
                    startActivity(new Intent(MainActivity.this, Registrati.class));
                    return true;
                } else if (item.getItemId() == R.id.preferiti) { // attenzione modificato provvisoriamente
                    startActivity(new Intent(MainActivity.this, Preferiti.class));
                    return true;
                } else if (item.getItemId() == R.id.biglietti) {
                    startActivity(new Intent(MainActivity.this, Biglietti.class));
                    return true;
                } else if (item.getItemId() == R.id.profilo) {
                    startActivity(new Intent(MainActivity.this, Profilo.class));
                    return true;
                }
                    return false;
                }
        });
    }
}

