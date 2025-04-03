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

        ImageButton btnBiglietto = findViewById(R.id.btnBiglietti3);
        ImageButton btnPreferiti = findViewById(R.id.btnPrefe3);
        ImageButton btnHome = findViewById(R.id.btnHome3);
        ImageButton btnAmici = findViewById(R.id.btnHomeAmici3);
        ImageButton btnProfilo = findViewById(R.id.btnProfilo3);

        btnBiglietto.setOnClickListener(v -> {
            Intent biglietto = new Intent(AmiciActivity.this, Biglietti.class);
            startActivity(biglietto);
        });

        btnProfilo.setOnClickListener(v -> {
            Intent profilo = new Intent(AmiciActivity.this, Profilo.class);
            startActivity(profilo);
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


        apiService = RetrofitClient.getApiService().create(ApiService.class);
        getUtentiRegistrati();
    }

    private void getUtentiRegistrati() {
        Call<List<Utente>> call = apiService.getUsers();
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
            }
        });
    }
}