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

import com.example.eventfinder.fragments.HomeFragment;

public class InfoEventiLp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_eventi_lp);

        ImageButton indietro = findViewById(R.id.btnBack);

        indietro.setOnClickListener(v -> {
            Intent amici = new Intent(InfoEventiLp.this, MainActivity.class);
            startActivity(amici);
        });



    }
}