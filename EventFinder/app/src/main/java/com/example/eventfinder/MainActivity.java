package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.eventfinder.fragments.BigliettiFragment;
import com.example.eventfinder.fragments.HomeFragment;
import com.example.eventfinder.fragments.PreferitiFragment;
import com.example.eventfinder.fragments.ProfiloFragment;
import com.example.eventfinder.modelli.Eventi;
import com.example.eventfinder.modelli.EventiAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnItemSelectedListener(navListener);

        Fragment selectedFragment = new HomeFragment(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

    }

    private final NavigationBarView.OnItemSelectedListener navListener = item -> {
        int itemId = item.getItemId();

        Fragment selectedFragment = null;

        if (itemId == R.id.nav_home) {
            selectedFragment = new HomeFragment(this);
        } else if (itemId == R.id.nav_preferiti) {
            selectedFragment = new PreferitiFragment();
        } else if (itemId == R.id.nav_biglietti) {
            selectedFragment = new BigliettiFragment();
        } else if (itemId == R.id.nav_profilo) {
            selectedFragment = new ProfiloFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

        return true;
    };
}


