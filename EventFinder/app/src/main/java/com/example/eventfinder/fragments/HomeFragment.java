package com.example.eventfinder.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.eventfinder.InfoEventi;
import com.example.eventfinder.Login;
import com.example.eventfinder.R;
import com.example.eventfinder.modelli.Eventi;
import com.example.eventfinder.modelli.EventiAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends DialogFragment {
    private ListView listView;
    private List<Eventi> eventiList;
    private EventiAdapter eventiAdapter;
    //private List<Eventi> eventiListFiltered; // Lista per gli eventi filtrati
    private Context ctx = null;

    public HomeFragment(Context ctx) {
        this.ctx = ctx;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        SearchView cerca = rootView.findViewById(R.id.searchView);
        Button loginButtonHome = rootView.findViewById(R.id.loginHomeBtn);

        ImageButton btnFiltro = rootView.findViewById(R.id.btnFiltro);

        // Imposta il listener per aprire il DialogFragment
        btnFiltro.setOnClickListener(v -> {
            FiltriDialogFragment filtriDialog = new FiltriDialogFragment();
            filtriDialog.show(getParentFragmentManager(), "FiltriDialog");
        });

        loginButtonHome.setOnClickListener(view -> {
            Intent intent = new Intent(ctx, Login.class);
            startActivity(intent);
        });

        listView = rootView.findViewById(R.id.eventiListView);
        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cerca.isIconified()) {
                    return;
                }
                cerca.setIconified(false);
            }
        });

        
        // Popola la lista degli eventi
        eventiList = new ArrayList<>();
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/l/linkin-park-biglietti.jpg", "Linkin Park", "mar 24 giugno, 16:00", "Ippodromo SNAI La Maura"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/teaser/222x222/2024/nazzi-nuova-storia-biglietti.jpg", "Stefano Nazzi - Indagini Live - Una nuova storia", "gio 03 Aprile, 21:01", "Teatro Arcimboldi"));
        eventiList.add(new Eventi("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGxeRyx5R-NwaPO_ad1mU9N-CKXpb7Z_NNjg&s", "Latin Festival ", "mar 03 Luglio, 23:01", "Segrate"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore"));

        // Inizializza la lista filtrata uguale alla lista originale


        // Configura l'adapter
        //eventiAdapter = new EventiAdapter(ctx, eventiListFiltered);
        eventiAdapter = new EventiAdapter(ctx, eventiList);
        listView.setAdapter(eventiAdapter);

        // Gestisci il click su un elemento della lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent infoIntent = new Intent(ctx, InfoEventi.class);
                infoIntent.putExtra("luogo", eventiList.get(position).getLuogo());
                startActivity(infoIntent);
            }
        });

        /*cerca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });*/

        return rootView;
    }


    /*private void filterList(String query) {
        eventiListFiltered.clear();

        if (query.isEmpty()) {
            eventiListFiltered.addAll(eventiList);
        } else {
            for (Eventi evento : eventiList) {
                if (evento.getTitolo().toLowerCase().contains(query.toLowerCase())) {
                    eventiListFiltered.add(evento);
                }
            }
        }
        eventiAdapter.notifyDataSetChanged();
    }*/
}
