package com.example.eventfinder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
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

public class InvitaAmiciActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UtentiAdapter utentiAdapter;
    private List<Utente> utentiList = new ArrayList<>();
    private ApiService getUtenti;

    private CheckBox seleziona;

    private Button btnInvita;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invita_amici);

        SharedPreference sharedPreference = new SharedPreference(this);

        recyclerView = findViewById(R.id.recyclerViewAmici);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        btnInvita = findViewById(R.id.btnInvita);

        utentiAdapter = new UtentiAdapter(utentiList,true);
        recyclerView.setAdapter(utentiAdapter);




        btnInvita.setOnClickListener(v -> {
            List<Utente> selezionati = utentiAdapter.getSelezionati();
            if (!selezionati.isEmpty()) {
                Toast.makeText(this, "Inviti mandati", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Nessun amico selezionato", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        getUtenti = RetrofitClient.getApiService().create(ApiService.class);
        Call<List<Utente>> call = getUtenti.getUsers();



        call.enqueue(new Callback<List<Utente>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Utente>> call, Response<List<Utente>> response) {
                if (response.isSuccessful()) {
                    utentiList.clear();
                    utentiList.addAll(response.body());
                    utentiAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(InvitaAmiciActivity.this, "Errore nel recupero degli utenti", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Utente>> call, Throwable t) {
                Toast.makeText(InvitaAmiciActivity.this, "Errore di rete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}