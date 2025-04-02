package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.example.eventfinder.modelli.Eventi;
import com.example.eventfinder.modelli.EventiAdapter;

import java.util.ArrayList;
import java.util.List;

public class Biglietti extends AppCompatActivity {
    private ListView listView;
    private List<Eventi> eventiList;
    private EventiAdapter eventiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biglietti);

        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

        Button programma = findViewById(R.id.eventiInProgramma);
        Button passati = findViewById(R.id.eventiPassati);
        ListView inProgramma = findViewById(R.id.listaInProgramma);
        ListView giaPassati = findViewById(R.id.listaPassati);

        ImageButton btnBiglietto = findViewById(R.id.btnBiglietti1);
        ImageButton btnPreferiti = findViewById(R.id.btnPrefe1);
        ImageButton btnHome = findViewById(R.id.btnHome1);
        ImageButton btnAmici = findViewById(R.id.btnHomeAmici1);
        ImageButton btnProfilo = findViewById(R.id.btnProfilo1);

        btnBiglietto.setOnClickListener(v -> {
            Intent biglietto = new Intent(Biglietti.this, Biglietti.class);
            startActivity(biglietto);
        });

        btnPreferiti.setOnClickListener(v -> {
            Intent preferiti = new Intent(Biglietti.this, Preferiti.class);
            startActivity(preferiti);

        });

        btnHome.setOnClickListener(v -> {
            Intent home = new Intent(Biglietti.this, HomeActivity.class);
            startActivity(home);
        });

        btnAmici.setOnClickListener(v -> {
            Intent amici = new Intent(Biglietti.this, AmiciActivity.class);
            startActivity(amici);
        });

        btnProfilo.setOnClickListener(v -> {
            Intent profilo = new Intent(Biglietti.this, Profilo.class);
            startActivity(profilo);
        });



        // Imposta la visibilità iniziale
        inProgramma.setVisibility(View.VISIBLE);
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
        /*Eventi[] prenotazioniInProgramma = {
                "Prenotazione A - 1 Marzo 2025",
                "Prenotazione B - 5 Marzo 2025",
                "Prenotazione C - 10 Marzo 2025"
        };

        String[] prenotazioniInPassate = {
                "Prenotazione 1 - 20 Febbraio 2025",
                "Prenotazione 2 - 22 Febbraio 2025",
                "Prenotazione 3 - 25 Febbraio 2025"
        };*/


        Eventi evento1 = new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/l/linkin-park-biglietti.jpg",
                "Linkin Park",
                "mar 24 giugno, 16:00",
                "Ippodromo SNAI La Maura",
                "92");
        Eventi evento2 = new Eventi("https://dice-media.imgix.net/attachments/2024-12-17/5bd0bf9c-7ffb-48d1-ba8f-9bb937748e69.jpg?rect=0%2C0%2C2064%2C2064&auto=format%2Ccompress&q=40&w=328&h=328&fit=crop&crop=faces%2Ccenter&dpr=2",
                "Vintage Violence",
                "15 Marzo 2024",
                "Legend Club, Milano",
                "92");

        List<Eventi> listaEventiInProgramma = new ArrayList<>();
        List<Eventi> listaEventiPassati = new ArrayList<>();
        listaEventiInProgramma.add(evento1);
        listaEventiPassati.add(evento2);

        // Crea gli ArrayAdapter per le ListView
        EventiAdapter adapter1 = new EventiAdapter(this,
                listaEventiInProgramma
        );

        EventiAdapter adapter2 = new EventiAdapter(this,
                listaEventiPassati
        );

        /*ArrayAdapter<Eventi> adapterInPassate = new ArrayAdapter<>(ctx,
                R.layout.lista_eventi,
                R.id.eventiTitolo,

        );*/

        // Imposta gli adapter per le ListView
        inProgramma.setAdapter(adapter1);
        //giaPassati.setAdapter(adapterInPassate);

        giaPassati.setAdapter(adapter2);
    }
}