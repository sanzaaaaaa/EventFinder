package com.example.eventfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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

        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

        Button sezAmici = findViewById(R.id.btnAmici);
        Button logout = findViewById(R.id.btnLogout);

        SharedPreference sharedPreference = new SharedPreference(this);

        ImageView profilo = findViewById(R.id.imgProfilo);
        ImageButton btnBiglietto = findViewById(R.id.btnBiglietti4);
        ImageButton btnPreferiti = findViewById(R.id.btnPrefe4);
        ImageButton btnHome = findViewById(R.id.btnHome4);
        ImageButton btnAmici = findViewById(R.id.btnHomeAmici4);
        ImageButton btnProfilo = findViewById(R.id.btnProfilo4);

        btnBiglietto.setOnClickListener(v -> {
            Intent biglietto = new Intent(Profilo.this, Biglietti.class);
            startActivity(biglietto);
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

        btnProfilo.setOnClickListener(v -> {
            Intent profili = new Intent(Profilo.this, Profilo.class);
            startActivity(profili);
        });

        // Usa Glide per caricare la GIF
        Glide.with(this)
                .asGif() // Specifica che vuoi caricare una GIF
                .load(R.drawable.iconautente) // Inserisci il nome della tua GIF nella cartella drawable
                .into(profilo); // Imposta la GIF nell'ImageView



        sezAmici.setOnClickListener(v -> {
            Intent amici = new Intent(Profilo.this, AmiciActivity.class);
            startActivity(amici);
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreference.setLoggedIn(false);

                Intent intent = new Intent(Profilo.this, HomeActivity.class);
                startActivity(intent);

            Toast.makeText(Profilo.this, "Hai eseguito il logout", Toast.LENGTH_SHORT).show();
            }

        });



    }
}



