package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eventfinder.fragments.HomeFragment;

public class InfoEventi extends AppCompatActivity {
    private boolean isFilled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_eventi);

        ImageButton indietro = findViewById(R.id.btnBack);
        ImageButton iconaPreferiti = findViewById(R.id.iconapreferitiInfoEvento);

        indietro.setOnClickListener(v -> {
            Intent amici = new Intent(InfoEventi.this, MainActivity.class);
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