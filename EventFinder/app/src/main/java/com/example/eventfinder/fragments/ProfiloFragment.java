package com.example.eventfinder.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.eventfinder.Amici;
import com.example.eventfinder.Profilo;
import com.example.eventfinder.R;

public class ProfiloFragment extends Fragment {

    Context ctx = null;

    public ProfiloFragment(Context ctx) {
        this.ctx = ctx;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {




        View rootView =  inflater.inflate(R.layout.fragment_profilo, container, false);


        Button sezAmici = rootView.findViewById(R.id.btnAmici);
        Button log = rootView.findViewById(R.id.btnLogout);


        ImageView profilo = rootView.findViewById(R.id.imgProfilo);

        // Usa Glide per caricare la GIF
        Glide.with(this)
                .asGif() // Specifica che vuoi caricare una GIF
                .load(R.drawable.iconautente) // Inserisci il nome della tua GIF nella cartella drawable
                .into(profilo); // Imposta la GIF nell'ImageView



        sezAmici.setOnClickListener(v -> {
            Intent amici = new Intent(ctx, Amici.class);
            startActivity(amici);
        });

        return rootView;
    }
}
