package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.example.eventfinder.modelli.Eventi;
import com.example.eventfinder.modelli.EventiAdapter;

import java.util.ArrayList;
import java.util.List;

public class Preferiti extends AppCompatActivity {
    private ListView preferitiListView;
    private List<Eventi> eventiList;
    private EventiAdapter eventiAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferiti);

        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

        ImageButton btnBiglietto = findViewById(R.id.btnBiglietti2);
        ImageButton btnHome = findViewById(R.id.btnHome2);
        ImageButton btnAmici = findViewById(R.id.btnHomeAmici2);
        ImageButton btnProfilo = findViewById(R.id.btnProfilo2);
        ListView preferitiListView = findViewById(R.id.preferitiListView);

        btnBiglietto.setOnClickListener(v -> {
            Intent biglietto = new Intent(Preferiti.this, Biglietti.class);
            startActivity(biglietto);
        });


        btnHome.setOnClickListener(v -> {
            Intent home = new Intent(Preferiti.this, HomeActivity.class);
            startActivity(home);
        });

        btnAmici.setOnClickListener(v -> {
            Intent amici = new Intent(Preferiti.this, AmiciActivity.class);
            startActivity(amici);
        });

        btnProfilo.setOnClickListener(v -> {
            Intent profilo = new Intent(Preferiti.this, Profilo.class);
            startActivity(profilo);
        });

        eventiList = new ArrayList<>();
        eventiAdapter = new EventiAdapter(this, eventiList);
        preferitiListView.setAdapter(eventiAdapter);

    }
}