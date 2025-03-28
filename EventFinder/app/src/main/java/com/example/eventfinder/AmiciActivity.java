package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventfinder.UtentiAdapter;
import com.example.eventfinder.modelli.ApiService;
import com.example.eventfinder.modelli.RetrofitClient;
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

        recyclerView = findViewById(R.id.recyclerViewAmici);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        utentiAdapter = new UtentiAdapter(utentiList);
        recyclerView.setAdapter(utentiAdapter);

        // Inizializza Retrofit e APIService
        apiService = RetrofitClient.getApiService().create(ApiService.class);

        // Chiamata per ottenere gli utenti registrati
        getUtentiRegistrati();
    }

    private void getUtentiRegistrati() {
        // Esegui la chiamata API per ottenere gli utenti
        Call<List<Utente>> call = apiService.getUsers(); // Usa la tua chiamata API
        call.enqueue(new Callback<List<Utente>>() {
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
                // Gestisci l'errore
            }
        });
    }
}