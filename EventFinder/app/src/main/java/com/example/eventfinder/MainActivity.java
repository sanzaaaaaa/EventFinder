package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventfinder.modelli.Eventi;
import com.example.eventfinder.modelli.EventiAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    List<Eventi> eventiList;
    private EventiAdapter eventiAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button loginButtonHome = findViewById(R.id.loginHomeBtn);
        loginButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        ListView listView = findViewById(R.id.eventiListView);


        eventiList = new ArrayList<Eventi>();
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/l/linkin-park-biglietti.jpg", "Linkin Park",
                "mar 24 giugno, 16:00", "Ippodromo SNAI La Maura"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/teaser/222x222/2024/nazzi-nuova-storia-biglietti.jpg", "Stefano Nazzi - Indagini Live - Una nuova storia",
                "gio 03 Aprile, 21:01", "Teatro Arcimboldi"));

        eventiAdapter = new EventiAdapter(this, eventiList);
        listView.setAdapter(eventiAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "tu sei: " + eventiList.get(position), Toast.LENGTH_LONG).show();

                Intent infoIntent = new Intent(MainActivity.this, InfoEventi.class);
                infoIntent.putExtra("luogo", eventiList.get(position).getLuogo());
                startActivity(infoIntent);
            }
        });
    }
}


