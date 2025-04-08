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
import com.example.eventfinder.modelli.SharedPreference;

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
        SharedPreference sharedPreference = new SharedPreference(this);

        Button programma = findViewById(R.id.eventiInProgramma);
        Button passati = findViewById(R.id.eventiPassati);
        ListView inProgramma = findViewById(R.id.listaInProgramma);
        ListView giaPassati = findViewById(R.id.listaPassati);

        ImageButton btnPreferiti = findViewById(R.id.btnPrefe1);
        ImageButton btnHome = findViewById(R.id.btnHome1);
        ImageButton btnAmici = findViewById(R.id.btnHomeAmici1);
        ImageButton btnProfilo = findViewById(R.id.btnProfilo1);


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
            if (sharedPreference.isLoggedIn()) {
                Intent profilo = new Intent(Biglietti.this, Profilo.class);
                startActivity(profilo);
            } else {
                Intent login = new Intent(Biglietti.this, Login.class);
                startActivity(login);
            }
        });




        inProgramma.setVisibility(View.VISIBLE);
        giaPassati.setVisibility(View.GONE);

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




        List<Eventi> listaEventiInProgramma = new ArrayList<>();
        List<Eventi> listaEventiPassati = new ArrayList<>();


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