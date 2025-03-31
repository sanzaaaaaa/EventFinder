package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;


import com.example.eventfinder.modelli.Eventi;
import com.example.eventfinder.modelli.EventiAdapter;
import com.example.eventfinder.modelli.SharedPreference;



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

        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

        SharedPreference sharedPreference = new SharedPreference(this);

        SearchView cerca = findViewById(R.id.searchView);
        Button loginButtonHome = findViewById(R.id.loginHomeBtn);
        ImageButton btnFiltro = findViewById(R.id.btnFiltro);
        listView = findViewById(R.id.eventiListView);
        ImageButton btnBiglietto = findViewById(R.id.btnBiglietti);
        ImageButton btnPreferiti = findViewById(R.id.btnPrefe);
        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnAmici = findViewById(R.id.btnHomeAmici);
        ImageButton btnProfilo = findViewById(R.id.btnProfilo);


        if (sharedPreference.isLoggedIn()) {
            loginButtonHome.setVisibility(View.GONE);  // Nasconde il pulsante di login
        } else {
            loginButtonHome.setVisibility(View.VISIBLE);
        }

        btnBiglietto.setOnClickListener(v -> {
            Intent biglietto = new Intent(HomeActivity.this, Biglietti.class);
            startActivity(biglietto);
        });

        btnPreferiti.setOnClickListener(v -> {
            Intent preferiti = new Intent(HomeActivity.this, Preferiti.class);
            startActivity(preferiti);

        });

        btnHome.setOnClickListener(v -> {
            Intent home = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(home);
        });

        btnAmici.setOnClickListener(v -> {
            Intent amici = new Intent(HomeActivity.this, AmiciActivity.class);
            startActivity(amici);
        });

        btnProfilo.setOnClickListener(v -> {
            Intent profilo = new Intent(HomeActivity.this, Profilo.class);
            startActivity(profilo);
        });

        btnFiltro.setOnClickListener(v -> {
            Intent filtro = new Intent(HomeActivity.this, Filtri.class);
            startActivity(filtro);
        });

        loginButtonHome.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, Login.class);
            startActivity(intent);
        });

        eventiList = new ArrayList<>();
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/l/linkin-park-biglietti.jpg", "Linkin Park", "mar 24 giugno, 16:00", "Ippodromo SNAI La Maura", "92"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/teaser/222x222/2024/nazzi-nuova-storia-biglietti.jpg", "Stefano Nazzi - Indagini Live - Una nuova storia", "gio 03 Aprile, 21:01", "Teatro Arcimboldi", "92"));
        eventiList.add(new Eventi("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGxeRyx5R-NwaPO_ad1mU9N-CKXpb7Z_NNjg&s", "Latin Festival ", "mar 03 Luglio, 23:01", "Segrate", "92"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore", "92"));
        eventiList.add(new Eventi("https://i.scdn.co/image/ab67616d0000b273c920263f076402b429b32606", "Artie five - tour La bella vita", "sab 20 agosto 2025, 21:00", "Mediolanum Forum", "92"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/l/linkin-park-biglietti.jpg", "Linkin Park", "mar 24 giugno, 16:00", "Ippodromo SNAI La Maura", "92"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/teaser/222x222/2024/nazzi-nuova-storia-biglietti.jpg", "Stefano Nazzi - Indagini Live - Una nuova storia", "gio 03 Aprile, 21:01", "Teatro Arcimboldi", "92"));
        eventiList.add(new Eventi("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGxeRyx5R-NwaPO_ad1mU9N-CKXpb7Z_NNjg&s", "Latin Festival ", "mar 03 Luglio, 23:01", "Segrate", "92"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore", "92"));
        eventiList.add(new Eventi("https://i.scdn.co/image/ab67616d0000b273c920263f076402b429b32606", "Artie five - tour La bella vita", "sab 20 agosto 2025, 21:00", "Mediolanum Forum", "92"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/l/linkin-park-biglietti.jpg", "Linkin Park", "mar 24 giugno, 16:00", "Ippodromo SNAI La Maura", "92"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/teaser/222x222/2024/nazzi-nuova-storia-biglietti.jpg", "Stefano Nazzi - Indagini Live - Una nuova storia", "gio 03 Aprile, 21:01", "Teatro Arcimboldi", "92"));
        eventiList.add(new Eventi("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGxeRyx5R-NwaPO_ad1mU9N-CKXpb7Z_NNjg&s", "Latin Festival ", "mar 03 Luglio, 23:01", "Segrate", "92"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/2/21savage-TA.jpg", "21 Savage ", "9 luglio 2025, 10:00", "Lido di Camaiore", "92"));
        eventiList.add(new Eventi("https://i.scdn.co/image/ab67616d0000b273c920263f076402b429b32606", "Artie five - tour La bella vita", "sab 20 agosto 2025, 21:00", "Mediolanum Forum", "92"));

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