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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.bumptech.glide.Glide;
import com.example.eventfinder.modelli.ApiService;
import com.example.eventfinder.modelli.RetrofitClient;
import com.example.eventfinder.modelli.SharedPreference;
import com.example.eventfinder.modelli.Utente;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoEventi extends AppCompatActivity {
    private boolean isFilled = false;

    private TextView titoloInfoEvento;
    private TextView dataInfoEvento;
    private TextView luogoInfoEvento;
    private ImageView immagineEvento;
    private TextView prezzoEvento;

    private Button invAmiciBtn;


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
        immagineEvento = findViewById(R.id.immagineEvento);
        prezzoEvento = findViewById(R.id.textPrezzo);
        invAmiciBtn = findViewById(R.id.invAmiciBtn);

        ImageButton indietro = findViewById(R.id.btnBack);
        ImageButton iconaPreferiti = findViewById(R.id.iconapreferitiInfoEvento);

        Intent intent = getIntent();
        String titolo = intent.getStringExtra("titolo");
        String data = intent.getStringExtra("data");
        String luogo = intent.getStringExtra("luogo");
        String immagineUrl = intent.getStringExtra("urlImage"); // Assicurati che la chiave sia corretta
        String prezzo = intent.getStringExtra("prezzo");

        titoloInfoEvento.setText(titolo);
        dataInfoEvento.setText(data);
        luogoInfoEvento.setText(luogo);
//        prezzoEvento.setText(prezzo);


        if (immagineUrl != null && !immagineUrl.isEmpty()) {
            Glide.with(this)
                    .load(immagineUrl)
                    .centerCrop()
                    .into(immagineEvento);
        } else {
            immagineEvento.setImageResource(R.drawable.barraricerca); // Immagine di fallback
        }

        indietro.setOnClickListener(v -> {
            Intent amici = new Intent(InfoEventi.this, HomeActivity.class);
            startActivity(amici);
        });

        invAmiciBtn.setOnClickListener(v -> {
            Intent invitaIntent = new Intent(this, InvitaAmiciActivity.class);
            startActivityForResult(intent, 2001);

        });

        /*iconaPreferiti.setOnClickListener(v -> {
            if (isFilled) {
                ApiService apiService = RetrofitClient.getApiService().create(ApiService.class);
                apiService.getEventi(1, 1).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(InfoEventi.this, "Aggiunto ai preferiti", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(InfoEventi.this, "Errore", Toast.LENGTH_SHORT).show();
                    }
                });

                iconaPreferiti.setImageResource(R.drawable.ic_pref_filled);
            } else {
                iconaPreferiti.setImageResource(R.drawable.ic_pref_not_filled);
            }

            isFilled = !isFilled;
        }); */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2001 && resultCode == RESULT_OK && data != null) {
            ArrayList<Utente> invitati = (ArrayList<Utente>) data.getSerializableExtra("amiciInvitati");

            if (invitati != null) {
                for (Utente u : invitati) {
                    Log.d("Utente invitato", u.getNome());
                }
                // Qui puoi anche aggiornare una TextView o RecyclerView per mostrare gli amici invitati
            }
        }
    }
}
