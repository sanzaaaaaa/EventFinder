package com.example.eventfinder.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.Button;

import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.eventfinder.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;



public class FiltriDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_filtri_dialog, container, false);



        SeekBar seekBar = view.findViewById(R.id.seekbar_prezzo);
        TextView txtPrezzo = view.findViewById(R.id.txt_prezzo);

        seekBar.setMax(350);
        //seekBar.setMin(10);
        seekBar.setProgress(350);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int prezzoMassimo =  progress;

                txtPrezzo.setText("Prezzo massimo: " + prezzoMassimo);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        Spinner categoriaSpinner = view.findViewById(R.id.spinner_categoria);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.categorie_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriaSpinner.setAdapter(adapter);

        Button btnApplica = view.findViewById(R.id.btn_applica);
        btnApplica.setOnClickListener(v -> {
            String prezzo = txtPrezzo.getText().toString(); // Leggi il prezzo

            dismiss();
        });

        Button btnReset = view.findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(v -> {
            seekBar.setProgress(0);
            txtPrezzo.setText("Prezzo massimo: 350");
        });

        return view;
    }
}
