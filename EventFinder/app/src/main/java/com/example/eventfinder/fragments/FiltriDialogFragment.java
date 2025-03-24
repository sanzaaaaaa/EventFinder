package com.example.eventfinder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.Spinner;

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

        Spinner categoriaSpinner = view.findViewById(R.id.spinner_categoria);
        DatePicker datePicker = view.findViewById(R.id.date_picker);
        AutoCompleteTextView luogoTextView = view.findViewById(R.id.autocomplete_luogo);
        SeekBar prezzoSeekBar = view.findViewById(R.id.seekbar_prezzo);
        Button btnApplica = view.findViewById(R.id.btn_applica);
        Button btnReset = view.findViewById(R.id.btn_reset);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner categoriaSpinner = view.findViewById(R.id.spinner_categoria);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.categorie_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriaSpinner.setAdapter(adapter);

        Button btnApplica = view.findViewById(R.id.btn_applica);
        btnApplica.setOnClickListener(v -> {
            // Logica per applicare i filtri
        });

        Button btnReset = view.findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(v -> {
            // Logica per resettare i filtri
        });
    }
}