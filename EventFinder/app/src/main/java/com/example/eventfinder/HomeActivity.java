package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;


import com.example.eventfinder.modelli.Eventi;
import com.example.eventfinder.modelli.EventiAdapter;
import com.example.eventfinder.Filtri;


import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ListView listView;
    private List<Eventi> eventiList;
    private List<Eventi> eventiListFiltered;
    private EventiAdapter eventiAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Mantieni lo stesso layout del fragment

        SearchView cerca = findViewById(R.id.searchView);
        Button loginButtonHome = findViewById(R.id.loginHomeBtn);
        ImageButton btnFiltro = findViewById(R.id.btnFiltro);
        listView = findViewById(R.id.eventiListView);

        btnFiltro.setOnClickListener(v -> {
            Intent filtro = new Intent(HomeActivity.this, Filtri.class);
            startActivity(filtro);
        });

        loginButtonHome.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, Login.class);
            startActivity(intent);
        });

        eventiList = new ArrayList<>();
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/l/linkin-park-biglietti.jpg", "Linkin Park", "mar 24 giugno, 16:00", "Ippodromo SNAI La Maura"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/teaser/222x222/2024/nazzi-nuova-storia-biglietti.jpg", "Stefano Nazzi - Indagini Live - Una nuova storia", "gio 03 Aprile, 21:01", "Teatro Arcimboldi"));
        eventiList.add(new Eventi("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGxeRyx5R-NwaPO_ad1mU9N-CKXpb7Z_NNjg&s", "Latin Festival ", "mar 03 Luglio, 23:01", "Segrate"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore"));
        eventiList.add(new Eventi("https://i.scdn.co/image/ab67616d0000b273c920263f076402b429b32606", "Artie five - tour La bella vita", "sab 20 agosto 2025, 21:00", "Mediolanum Forum"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/l/linkin-park-biglietti.jpg", "Linkin Park", "mar 24 giugno, 16:00", "Ippodromo SNAI La Maura"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/teaser/222x222/2024/nazzi-nuova-storia-biglietti.jpg", "Stefano Nazzi - Indagini Live - Una nuova storia", "gio 03 Aprile, 21:01", "Teatro Arcimboldi"));
        eventiList.add(new Eventi("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGxeRyx5R-NwaPO_ad1mU9N-CKXpb7Z_NNjg&s", "Latin Festival ", "mar 03 Luglio, 23:01", "Segrate"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore"));
        eventiList.add(new Eventi("https://i.scdn.co/image/ab67616d0000b273c920263f076402b429b32606", "Artie five - tour La bella vita", "sab 20 agosto 2025, 21:00", "Mediolanum Forum"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/l/linkin-park-biglietti.jpg", "Linkin Park", "mar 24 giugno, 16:00", "Ippodromo SNAI La Maura"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/teaser/222x222/2024/nazzi-nuova-storia-biglietti.jpg", "Stefano Nazzi - Indagini Live - Una nuova storia", "gio 03 Aprile, 21:01", "Teatro Arcimboldi"));
        eventiList.add(new Eventi("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGxeRyx5R-NwaPO_ad1mU9N-CKXpb7Z_NNjg&s", "Latin Festival ", "mar 03 Luglio, 23:01", "Segrate"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore"));
        eventiList.add(new Eventi("https://i.scdn.co/image/ab67616d0000b273c920263f076402b429b32606", "Artie five - tour La bella vita", "sab 20 agosto 2025, 21:00", "Mediolanum Forum"));

        eventiListFiltered = new ArrayList<>(eventiList);

        eventiAdapter = new EventiAdapter(this, eventiListFiltered);
        listView.setAdapter(eventiAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent infoIntent = new Intent(this, InfoEventi.class);
            infoIntent.putExtra("titolo", eventiList.get(position).getTitolo());
            infoIntent.putExtra("data", eventiList.get(position).getData());
            infoIntent.putExtra("urlImage", eventiList.get(position).getUrlImage());
            infoIntent.putExtra("luogo", eventiList.get(position).getLuogo());
            startActivity(infoIntent);
        });

        cerca.setOnClickListener(v -> {
            if (!cerca.isIconified()) {
                return;
            }
            cerca.setIconified(false);
        });

        cerca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void filterList(String query) {
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
    }
}