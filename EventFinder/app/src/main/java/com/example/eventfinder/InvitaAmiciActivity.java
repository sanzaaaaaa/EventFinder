package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventfinder.modelli.Utente;

import java.util.ArrayList;
import java.util.List;

public class InvitaAmiciActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btnInvita;
    AmiciInvitaAdapter adapter;
    List<Utente> listaAmici = new ArrayList<>(); // Riempita con Retrofit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invita_amici);

        recyclerView = findViewById(R.id.recyclerViewAmici);
        btnInvita = findViewById(R.id.btnInvita);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        caricaAmici(); // Retrofit o come già fai nell’altra activity

        btnInvita.setOnClickListener(v -> {
            ArrayList<Utente> selezionati = new ArrayList<>(adapter.getSelezionati());
            Intent resultIntent = new Intent();
            resultIntent.putExtra("amiciInvitati", selezionati);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    private void caricaAmici() {
        // Esattamente come già fai nella schermata Amici
        // Una volta ottenuti:
        // listaAmici = response.body();
        adapter = new AmiciInvitaAdapter(listaAmici);
        recyclerView.setAdapter(adapter);
    }
}