package com.example.eventfinder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eventfinder.R;

public class BigliettiFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment_biglietti, container, false);

     //   Button programma = rootView.findViewById(R.id.eventiInProgramma);
       // Button passati = rootView.findViewById(R.id.eventiPassati);
      //  ListView inProgramma = rootView.findViewById(R.id.listaInProgramma);
       // ListView giaPassati = rootView.findViewById(R.id.listaPassati);





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


         return rootView;
    }
}
