package com.example.eventfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eventfinder.R;
import com.example.eventfinder.modelli.Utente;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UtentiAdapter extends RecyclerView.Adapter<UtentiAdapter.UtenteViewHolder> {
    private List<Utente> utenti;
    private List<Utente> utentiFull;
    private Set<Utente> selezionati = new HashSet<>();
    private boolean showCheckbox;
    public UtentiAdapter(List<Utente> utenti, boolean showCheckbox) {
        this.utenti = utenti;
        this.utentiFull = new ArrayList<>(utenti);
        this.showCheckbox = showCheckbox;

    }

    public List<Utente> getSelezionati() {
        return new ArrayList<>(selezionati);
    }

    public void setData(List<Utente> nuovaLista) {
        utenti = nuovaLista;
        utentiFull = new ArrayList<>(nuovaLista);
        notifyDataSetChanged();
    }

    public void filter(String testo) {

        if (testo.isEmpty()) {
            utenti.clear();
            utenti.addAll(utentiFull);
        } else {

            List<Utente> filtrata = new ArrayList<>();
            for (Utente u : utentiFull) {

                String nomeLower = u.getNome().toLowerCase();
                String cognomeLower = u.getCognome().toLowerCase();
                String testoLower = testo.toLowerCase();

                if (nomeLower.contains(testoLower) || cognomeLower.contains(testoLower)) {
                    filtrata.add(u);
                }
            }

            utenti.clear();
            utenti.addAll(filtrata);
        }

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public UtenteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_amico_checkbox, parent, false);
        return new UtenteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UtenteViewHolder holder, int position) {
        Utente utente = utenti.get(position);

        holder.nome.setText(utente.getNome());
        holder.cognome.setText(utente.getCognome());

        if (showCheckbox) {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setOnCheckedChangeListener(null);
            holder.checkBox.setChecked(selezionati.contains(utente));
            holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    selezionati.add(utente);
                } else {
                    selezionati.remove(utente);
                }
            });
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return utenti.size();
    }

    public static class UtenteViewHolder extends RecyclerView.ViewHolder {
        TextView nome, cognome;
        CheckBox checkBox;

        public UtenteViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nomeUtente);
            cognome = itemView.findViewById(R.id.cognomeUtente);
            checkBox = itemView.findViewById(R.id.checkboxAmico);


        }
    }
}
