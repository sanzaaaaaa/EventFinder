package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.example.eventfinder.modelli.Amici;

public class AmiciActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amici);

        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

        ImageButton back = findViewById(R.id.btnBack);
        ImageButton btnBiglietto = findViewById(R.id.btnBiglietti3);
        ImageButton btnPreferiti = findViewById(R.id.btnPrefe3);
        ImageButton btnHome = findViewById(R.id.btnHome3);
        ImageButton btnAmici = findViewById(R.id.btnHomeAmici3);
        ImageButton btnProfilo = findViewById(R.id.btnProfilo3);

        btnBiglietto.setOnClickListener(v -> {
            Intent biglietto = new Intent(AmiciActivity.this, Biglietti.class);
            startActivity(biglietto);
        });

        btnPreferiti.setOnClickListener(v -> {
            Intent preferiti = new Intent(AmiciActivity.this, Preferiti.class);
            startActivity(preferiti);

        });

        btnHome.setOnClickListener(v -> {
            Intent home = new Intent(AmiciActivity.this, HomeActivity.class);
            startActivity(home);
        });

        btnAmici.setOnClickListener(v -> {
            Intent amici = new Intent(AmiciActivity.this, Amici.class);
            startActivity(amici);
        });

        btnProfilo.setOnClickListener(v -> {
            Intent profilo = new Intent(AmiciActivity.this, Profilo.class);
            startActivity(profilo);
        });

        back.setOnClickListener(v -> {
            Intent dietro = new Intent(AmiciActivity.this, HomeActivity.class);
            startActivity(dietro);
        });
    }
}