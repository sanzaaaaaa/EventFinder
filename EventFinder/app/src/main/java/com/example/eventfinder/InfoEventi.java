package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eventfinder.fragments.HomeFragment;

public class InfoEventi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_eventi);

        ImageButton indietro = findViewById(R.id.btnBack);

        indietro.setOnClickListener(v -> {
            Intent amici = new Intent(InfoEventi.this, MainActivity.class);
            startActivity(amici);
        });



    }
}