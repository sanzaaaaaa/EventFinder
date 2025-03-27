package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eventfinder.modelli.Home;

public class InfoEventi extends AppCompatActivity {
    private boolean isFilled = false;

    private TextView titoloInfoEvento;
    private TextView dataInfoEvento;
    private TextView luogoInfoEvento;
    private ImageView immagineEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_eventi);
        titoloInfoEvento = findViewById(R.id.titoloInfoEvento); // Corretto
        dataInfoEvento = findViewById(R.id.dataInfoEvento);     // Corretto
        luogoInfoEvento = findViewById(R.id.luogoInfoEvento);   // Corretto
        immagineEvento = findViewById(R.id.immagineEvento);

        ImageButton indietro = findViewById(R.id.btnBack);
        ImageButton iconaPreferiti = findViewById(R.id.iconapreferitiInfoEvento);

        Intent intent = getIntent();
        String titolo = intent.getStringExtra("titolo");
        String data = intent.getStringExtra("data");
        String luogo = intent.getStringExtra("luogo");
        String immagineUrl = intent.getStringExtra("immagine");

        titoloInfoEvento.setText(titolo);
        dataInfoEvento.setText(data);
        luogoInfoEvento.setText(luogo);


        indietro.setOnClickListener(v -> {
            Intent amici = new Intent(InfoEventi.this, Home.class);
            startActivity(amici);
        });

        iconaPreferiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFilled) {
                    iconaPreferiti.setImageResource(R.drawable.ic_pref_filled);
                } else {
                    iconaPreferiti.setImageResource(R.drawable.ic_pref_not_filled);
                }

                isFilled =! isFilled;
            }
        });

    }
}