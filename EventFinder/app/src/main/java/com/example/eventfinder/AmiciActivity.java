package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AmiciActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amici);

        ImageButton back = findViewById(R.id.btnBack);


        back.setOnClickListener(v -> {
            Intent dietro = new Intent(AmiciActivity.this, HomeActivity.class);
            startActivity(dietro);
        });
    }
}