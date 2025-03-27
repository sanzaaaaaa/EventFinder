package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventfinder.fragments.ProfiloFragment;
import com.example.eventfinder.modelli.Home;

public class Amici extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amici);

        ImageButton back = findViewById(R.id.btnBack);


        back.setOnClickListener(v -> {
            Intent dietro = new Intent(Amici.this, Home.class);
            startActivity(dietro);
        });
    }
}