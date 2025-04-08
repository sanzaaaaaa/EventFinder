package com.example.eventfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventfinder.modelli.Utente;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AmiciInvitaAdapter extends RecyclerView.Adapter<AmiciInvitaAdapter.ViewHolder> {
    private List<Utente> amici;
    private Set<Utente> selezionati = new HashSet<>();

    public AmiciInvitaAdapter(List<Utente> amici) {
        this.amici = amici;
    }

    public Set<Utente> getSelezionati() {
        return selezionati;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amico_checkbox, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Utente amico = amici.get(position);
        holder.checkBox.setText(amico.getNome());

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(selezionati.contains(amico));

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selezionati.add(amico);
            } else {
                selezionati.remove(amico);
            }
        });
    }

    @Override
    public int getItemCount() {
        return amici.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkboxAmico);
        }
    }
}