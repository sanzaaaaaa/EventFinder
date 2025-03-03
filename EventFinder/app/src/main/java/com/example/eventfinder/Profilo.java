package com.example.eventfinder;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;


import androidx.appcompat.app.AppCompatActivity;

import java.util.stream.DoubleStream;

public class Profilo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        Button sezAmici = findViewById(R.id.btnAmici);
        Button log = findViewById(R.id.btnLogout);


        ImageView profilo = findViewById(R.id.imgProfilo);

        // Usa Glide per caricare la GIF
        Glide.with(this)
                .asGif() // Specifica che vuoi caricare una GIF
                .load(R.drawable.iconautente) // Inserisci il nome della tua GIF nella cartella drawable
                .into(profilo); // Imposta la GIF nell'ImageView
    }
}



