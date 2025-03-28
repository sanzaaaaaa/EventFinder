package com.example.eventfinder;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class Filtri extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtri); // Usa lo stesso layout

        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

        SeekBar seekBar = findViewById(R.id.seekbar_prezzo);
        TextView txtPrezzo = findViewById(R.id.txt_prezzo);
        Spinner categoriaSpinner = findViewById(R.id.spinner_categoria);
        Button btnApplica = findViewById(R.id.btn_applica);
        Button btnReset = findViewById(R.id.btn_reset);

        seekBar.setMax(350);
        seekBar.setProgress(350);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtPrezzo.setText("Prezzo massimo: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorie_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriaSpinner.setAdapter(adapter);

        btnApplica.setOnClickListener(v -> {
            String prezzo = txtPrezzo.getText().toString(); // Leggi il prezzo
            finish(); // Chiudi l'activity dopo l'applicazione dei filtri
        });

        btnReset.setOnClickListener(v -> {
            seekBar.setProgress(0);
            txtPrezzo.setText("Prezzo massimo: 350");
        });
    }
}