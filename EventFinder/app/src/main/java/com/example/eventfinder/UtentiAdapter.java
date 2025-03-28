package com.example.eventfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eventfinder.R;
import com.example.eventfinder.modelli.Utente;
import java.util.List;

public class UtentiAdapter extends RecyclerView.Adapter<UtentiAdapter.UtenteViewHolder> {
    private List<Utente> utenti;

    public UtentiAdapter(List<Utente> utenti) {
        this.utenti = utenti;
    }

    @NonNull
    @Override
    public UtenteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amico, parent, false);
        return new UtenteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UtenteViewHolder holder, int position) {
        Utente utente = utenti.get(position);
        holder.nome.setText(utente.getNome());
        holder.cognome.setText(utente.getCognome());
    }

    @Override
    public int getItemCount() {
        return utenti.size();
    }

    public class UtenteViewHolder extends RecyclerView.ViewHolder {
        TextView nome, cognome;

        public UtenteViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nomeUtente);
            cognome = itemView.findViewById(R.id.cognomeUtente);
        }
    }
}