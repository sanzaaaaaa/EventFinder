package com.example.eventfinder.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eventfinder.R;
import com.example.eventfinder.modelli.Eventi;
import com.example.eventfinder.modelli.EventiAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BigliettiFragment extends Fragment {
    private ListView listView;
    private List<Eventi> eventiList;
    private EventiAdapter eventiAdapter;
    private Context ctx = null;

    public BigliettiFragment(Context ctx) {
        this.ctx = ctx;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment_biglietti, container, false);

         Button programma = rootView.findViewById(R.id.eventiInProgramma);
         Button passati = rootView.findViewById(R.id.eventiPassati);
         ListView inProgramma = rootView.findViewById(R.id.listaInProgramma);
         ListView giaPassati = rootView.findViewById(R.id.listaPassati);

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
                "Ippodromo SNAI La Maura");
        Eventi evento2 = new Eventi("https://dice-media.imgix.net/attachments/2024-12-17/5bd0bf9c-7ffb-48d1-ba8f-9bb937748e69.jpg?rect=0%2C0%2C2064%2C2064&auto=format%2Ccompress&q=40&w=328&h=328&fit=crop&crop=faces%2Ccenter&dpr=2",
                "Vintage Violence",
                "15 Marzo 2024",
                "Legend Club, Milano");

        List<Eventi> listaEventiInProgramma = new ArrayList<>();
        List<Eventi> listaEventiPassati = new ArrayList<>();
        listaEventiInProgramma.add(evento1);
        listaEventiPassati.add(evento2);

        // Crea gli ArrayAdapter per le ListView
        EventiAdapter adapter1 = new EventiAdapter(ctx,
                listaEventiInProgramma
        );

        EventiAdapter adapter2 = new EventiAdapter(ctx,
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

         return rootView;
    }
}
