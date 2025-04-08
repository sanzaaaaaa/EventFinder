package com.example.eventfinder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.eventfinder.modelli.ApiService;
import com.example.eventfinder.modelli.RetrofitClient;
import com.example.eventfinder.modelli.SharedPreference;
import com.example.eventfinder.modelli.Utente;



import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AmiciActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UtentiAdapter utentiAdapter;
    private List<Utente> utentiList = new ArrayList<>();
    private ApiService apiService; // Usa Retrofit per la tua chiamata API

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amici);
        SharedPreference sharedPreference = new SharedPreference(this);
        recyclerView = findViewById(R.id.recyclerViewAmici);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        utentiAdapter = new UtentiAdapter(utentiList);
        recyclerView.setAdapter(utentiAdapter);


        apiService = RetrofitClient.getApiService().create(ApiService.class);
        getUtentiRegistrati();

        ImageButton indietro = findViewById(R.id.btnBack);

        indietro.setOnClickListener(v -> {
            Intent amici = new Intent(AmiciActivity.this, HomeActivity.class);
            startActivity(amici);
        });

        ImageButton btnBiglietto = findViewById(R.id.btnBiglietti3);
        ImageButton btnPreferiti = findViewById(R.id.btnPrefe3);
        ImageButton btnHome = findViewById(R.id.btnHome3);
        ImageButton btnAmici = findViewById(R.id.btnHomeAmici3);
        ImageButton btnProfilo = findViewById(R.id.btnProfilo3);
        SearchView cerca = findViewById(R.id.searchViewAmici);


        cerca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                utentiAdapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                utentiAdapter.filter(newText);
                return true;
            }
        });


        btnBiglietto.setOnClickListener(v -> {
            Intent biglietto = new Intent(AmiciActivity.this, Biglietti.class);
            startActivity(biglietto);
        });

        btnPreferiti.setOnClickListener(v -> {
            Intent preferiti = new Intent(AmiciActivity.this, Preferiti.class);
            startActivity(preferiti);

        });

        btnHome.setOnClickListener(v -> {
            Intent home = new Intent(AmiciActivity.this, HomeActivity.class);
            startActivity(home);
        });

        btnAmici.setOnClickListener(v -> {
            Intent amici = new Intent(AmiciActivity.this, AmiciActivity.class);
            startActivity(amici);
        });

        btnProfilo.setOnClickListener(v -> {
            if (sharedPreference.isLoggedIn()) {
                Intent profilo = new Intent(AmiciActivity.this, Profilo.class);
                startActivity(profilo);
            } else {
                Intent login = new Intent(AmiciActivity.this, Login.class);
                startActivity(login);
            }
        });

    }


    private void getUtentiRegistrati() {
        Call<List<Utente>> call = apiService.getUsers();
        call.enqueue(new Callback<List<Utente>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Utente>> call, Response<List<Utente>> response) {
                if (response.isSuccessful()) {
                    utentiList.clear();
                    utentiList.addAll(response.body());
                    utentiAdapter.notifyDataSetChanged();
                }
            }





            @Override
            public void onFailure(Call<List<Utente>> call, Throwable t) {
            }
        });
    }

}