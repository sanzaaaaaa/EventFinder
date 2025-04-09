package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.example.eventfinder.modelli.ApiService;
import com.example.eventfinder.modelli.Eventi;
import com.example.eventfinder.modelli.EventiAdapter;
import com.example.eventfinder.modelli.RetrofitClient;
import com.example.eventfinder.modelli.SharedPreference;
import com.example.eventfinder.modelli.Utente;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Preferiti extends AppCompatActivity {
    private ListView preferitiListView;
    private List<Eventi> preferitiList = new ArrayList<>();
    private EventiAdapter eventiAdapter;
    private ApiService apiService;
    private SharedPreference sharedPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferiti);

        SharedPreference sharedPreference = new SharedPreference(this);

        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

        ImageButton btnBiglietto = findViewById(R.id.btnBiglietti2);
        ImageButton btnHome = findViewById(R.id.btnHome2);
        ImageButton btnAmici = findViewById(R.id.btnHomeAmici2);
        ImageButton btnProfilo = findViewById(R.id.btnProfilo2);
        ListView preferitiListView = findViewById(R.id.preferitiListView);

        eventiAdapter = new EventiAdapter(this, preferitiList);
        preferitiListView.setAdapter(eventiAdapter);

        int utenteId = sharedPreference.getId();

        btnBiglietto.setOnClickListener(v -> {
            Intent biglietto = new Intent(Preferiti.this, Biglietti.class);
            startActivity(biglietto);
        });


        btnHome.setOnClickListener(v -> {
            Intent home = new Intent(Preferiti.this, HomeActivity.class);
            startActivity(home);

        });

        btnAmici.setOnClickListener(v -> {
            Intent amici = new Intent(Preferiti.this, AmiciActivity.class);
            startActivity(amici);
        });

        btnProfilo.setOnClickListener(v -> {
            if (sharedPreference.isLoggedIn()) {
                Intent profilo = new Intent(Preferiti.this, Profilo.class);
                startActivity(profilo);
            } else {
                Intent login = new Intent(Preferiti.this, Login.class);
                startActivity(login);
            }
        });

        apiService = RetrofitClient.getApiService().create(ApiService.class);
        Call<List<Eventi>> call = apiService.getPreferiti(utenteId);
        call.enqueue(new Callback<List<Eventi>>() {
            @Override
            public void onResponse(Call<List<Eventi>> call, Response<List<Eventi>> response) {
                preferitiList.clear();
                preferitiList.addAll(response.body());
                eventiAdapter.notifyDataSetChanged();
                Log.d("PREFERITI", "Ricevuti: " + response.body().size());
            }

            @Override
            public void onFailure(Call<List<Eventi>> call, Throwable t) {
                Toast.makeText(Preferiti.this, "Errore durante il recupero dei preferiti", Toast.LENGTH_SHORT).show();
            }
        });


    }
}