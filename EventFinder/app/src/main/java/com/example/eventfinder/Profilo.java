package com.example.eventfinder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.eventfinder.modelli.Amici;
import com.example.eventfinder.modelli.SharedPreference;

public class Profilo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        TextView nomeProfilo = findViewById(R.id.nome);
        TextView dataNascitaProfilo = findViewById(R.id.datanascita);
        Button logout = findViewById(R.id.btnLogout);

        ImageButton btnBiglietto = findViewById(R.id.btnBiglietti4);
        ImageButton btnPreferiti = findViewById(R.id.btnPrefe4);
        ImageButton btnHome = findViewById(R.id.btnHome4);
        ImageButton btnAmici = findViewById(R.id.btnHomeAmici4);
        ImageButton btnProfilo = findViewById(R.id.btnProfilo4);

        btnBiglietto.setOnClickListener(v -> {
            Intent biglietto = new Intent(Profilo.this, Biglietti.class);
            startActivity(biglietto);
        });

        btnProfilo.setOnClickListener(v -> {
            Intent profilo = new Intent(Profilo.this, Profilo.class);
            startActivity(profilo);
        });

        btnPreferiti.setOnClickListener(v -> {
            Intent preferiti = new Intent(Profilo.this, Preferiti.class);
            startActivity(preferiti);

        });

        btnHome.setOnClickListener(v -> {
            Intent home = new Intent(Profilo.this, HomeActivity.class);
            startActivity(home);
        });

        btnAmici.setOnClickListener(v -> {
            Intent amici = new Intent(Profilo.this, AmiciActivity.class);
            startActivity(amici);
        });


        SharedPreference sharedPreference = new SharedPreference(this);

        if (sharedPreference.isLoggedIn()) {
            String nome = sharedPreference.getNome();
            String cognome = sharedPreference.getCognome();
            String dataNascita = sharedPreference.getDataDiNascita();

            nomeProfilo.setText(nome + " " + cognome);
            dataNascitaProfilo.setText(dataNascita);
        } else {
            Intent intent = new Intent(Profilo.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        logout.setOnClickListener(v -> {
            sharedPreference.setLoggedIn(false);
            Intent intent = new Intent(Profilo.this, Login.class);
            startActivity(intent);
            finish();
        });
    }
}




