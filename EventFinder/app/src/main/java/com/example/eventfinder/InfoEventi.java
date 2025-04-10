package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.bumptech.glide.Glide;
import com.example.eventfinder.modelli.ApiService;
import com.example.eventfinder.modelli.Eventi;
import com.example.eventfinder.modelli.EventiBiglietti;
import com.example.eventfinder.modelli.EventiPreferiti;
import com.example.eventfinder.modelli.RetrofitClient;
import com.example.eventfinder.modelli.SharedPreference;
import com.example.eventfinder.modelli.Utente;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoEventi extends AppCompatActivity {
    private boolean isFilled = false;
    private EventiPreferiti preferiti;
    private TextView titoloInfoEvento;
    private TextView dataInfoEvento;
    private TextView luogoInfoEvento;
    private TextView infoArtista;
    private TextView infoEvento;
    private ImageView immagineEvento;
    private TextView prezzoEvento;
    private EventiBiglietti biglietti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_eventi);

        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

        titoloInfoEvento = findViewById(R.id.titoloInfoEvento);
        dataInfoEvento = findViewById(R.id.dataInfoEvento);
        luogoInfoEvento = findViewById(R.id.luogoInfoEvento);
        infoArtista = findViewById(R.id.artistaInfoEvento);
        infoEvento = findViewById(R.id.infoEvento);
        immagineEvento = findViewById(R.id.immagineEvento);
        prezzoEvento = findViewById(R.id.textPrezzo);
        ImageButton indietro = findViewById(R.id.btnBack);
        ImageButton iconaPreferiti = findViewById(R.id.iconapreferitiInfoEvento);
        Button btnbiglietti = findViewById(R.id.compraBtn);

        Intent intent = getIntent();
        String titolo = intent.getStringExtra("titolo");
        String data = intent.getStringExtra("data");
        String luogo = intent.getStringExtra("luogo");
        String info_evento = intent.getStringExtra("info_evento");
        String info_artista = intent.getStringExtra("info_artista");
        String immagineUrl = intent.getStringExtra("urlImage");
        String prezzo = intent.getStringExtra("prezzo");

        titoloInfoEvento.setText(titolo);
        dataInfoEvento.setText(data);
        luogoInfoEvento.setText(luogo);
        infoArtista.setText(info_artista);
        infoEvento.setText(info_evento);
        prezzoEvento.setText(prezzo);

        SharedPreference sharedPreference = new SharedPreference(this);



        if (immagineUrl != null && !immagineUrl.isEmpty()) {
            Glide.with(this)
                    .load(immagineUrl)
                    .centerCrop()
                    .into(immagineEvento);
        } else {
            immagineEvento.setImageResource(R.drawable.barraricerca);
        }

        indietro.setOnClickListener(v -> {
            Intent amici = new Intent(InfoEventi.this, HomeActivity.class);
            startActivity(amici);
        });

        biglietti = new EventiBiglietti(sharedPreference.getId(), intent.getIntExtra("evento_id", -1));
        btnbiglietti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService = RetrofitClient.getApiService().create(ApiService.class);
                apiService.postBiglietti(biglietti).enqueue(new Callback<List<Void>>() {
                    @Override
                    public void onResponse(Call<List<Void>> call, Response<List<Void>> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(InfoEventi.this, "Biglietto acquistato", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Void>> call, Throwable t) {
                        Log.e("API_ERROR", "Errore: " + t.getMessage(), t);
                        Toast.makeText(InfoEventi.this, "Errore di rete", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        ApiService apiService = RetrofitClient.getApiService().create(ApiService.class);
        int eventoId = getIntent().getIntExtra("evento_id", -1);
        int utenteId = sharedPreference.getId();

        if (eventoId != -1) {
            apiService.getPreferiti(utenteId).enqueue(new Callback<List<Eventi>>() {
                @Override
                public void onResponse(Call<List<Eventi>> call, Response<List<Eventi>> response) {
                    if (response.isSuccessful()) {
                        List<Eventi> preferiti = response.body();
                        boolean isEventInFavorites = false;
                        for (Eventi evento : preferiti) {
                            if (evento.getId() == eventoId) {
                                iconaPreferiti.setImageResource(R.drawable.ic_pref_filled);
                                isFilled = true;
                                isEventInFavorites = true;
                                break;
                            }
                        }

                        if (!isEventInFavorites) {
                            iconaPreferiti.setImageResource(R.drawable.ic_pref_not_filled);
                            isFilled = false;
                        }
                    } else {
                        Toast.makeText(InfoEventi.this, "Errore nel recupero dei preferiti", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Eventi>> call, Throwable t) {
                    Toast.makeText(InfoEventi.this, "Errore di rete", Toast.LENGTH_SHORT).show();
                }
            });
        }



        iconaPreferiti.setOnClickListener(v -> {
            EventiPreferiti preferiti = new EventiPreferiti(utenteId, eventoId);

            if (!isFilled){
                if (eventoId == -1) {
                    Toast.makeText(InfoEventi.this, "Errore: ID evento mancante", Toast.LENGTH_SHORT).show();
                    return;
                }
                apiService.getEvents(preferiti).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            iconaPreferiti.setImageResource(R.drawable.ic_pref_filled);
                            isFilled = true;
                            Toast.makeText(InfoEventi.this, "Evento aggiunto ai preferiti", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(InfoEventi.this, "Errore durante l'aggiunta' dell'evento ai preferiti", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(InfoEventi.this, "Errore di rete", Toast.LENGTH_SHORT).show();
                    }
                });

            } else {

                apiService.deleteEvents(preferiti).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            iconaPreferiti.setImageResource(R.drawable.ic_pref_not_filled);
                            isFilled = false;
                            Toast.makeText(InfoEventi.this, "Evento rimosso dai preferiti", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(InfoEventi.this, "Errore durante la rimozione dai preferiti", Toast.LENGTH_SHORT).show();
                    }
                });
            }



        });
    }
}
