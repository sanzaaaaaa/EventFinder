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

import com.example.eventfinder.modelli.Eventi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Biglietti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biglietti);
        Button programma = findViewById(R.id.eventiInProgramma);
        Button passati = findViewById(R.id.eventiPassati);
        ListView inProgramma = findViewById(R.id.listaInProgramma);
        ListView giaPassati = findViewById(R.id.listaPassati);





        // Imposta la visibilità iniziale
        /* inProgramma.setVisibility(View.VISIBLE);
        giaPassati.setVisibility(View.GONE);

        // Imposta i listener per i bottoni
        programma.setOnClickListener(v -> {
            inProgramma.setVisibility(View.VISIBLE);
            giaPassati.setVisibility(View.GONE);
        });

        passati.setOnClickListener(v -> {
            giaPassati.setVisibility(View.VISIBLE);
            inProgramma.setVisibility(View.GONE);
        });

        // Dati per le prenotazioni
        String[] prenotazioniInProgramma = {
                "Prenotazione A - 1 Marzo 2025",
                "Prenotazione B - 5 Marzo 2025",
                "Prenotazione C - 10 Marzo 2025"
        };

        String[] prenotazioniInPassate = {
                "Prenotazione 1 - 20 Febbraio 2025",
                "Prenotazione 2 - 22 Febbraio 2025",
                "Prenotazione 3 - 25 Febbraio 2025"
        };
        // Crea gli ArrayAdapter per le ListView
       /* ArrayAdapter<Eventi> adapterInProgramma = new ArrayAdapter<>(this,
                R.layout.lista_eventi,
                R.id.eventiTitolo,
                prenotazioniInProgramma
        );

        ArrayAdapter<Eventi> adapterInPassate = new ArrayAdapter<>(this,
                R.layout.lista_eventi,
                R.id.eventiTitolo,
                prenotazioniInPassate
        );

        // Imposta gli adapter per le ListView
        inProgramma.setAdapter(adapterInProgramma);
        giaPassati.setAdapter(adapterInPassate); */
    }
}
