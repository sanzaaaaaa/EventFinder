package com.example.eventfinder.modelli;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.eventfinder.R;

import java.util.List;

public class EventiAdapter extends ArrayAdapter<Eventi> {
    private Context context;

    private boolean isFilled = false;

    public EventiAdapter(Context context,  List<Eventi> eventiList) {
        super(context, 0, eventiList);
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Eventi eventi = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lista_eventi, parent, false);
        }

        ImageView eventiImageView = convertView.findViewById(R.id.eventiImageView);
        TextView eventiTitolo = convertView.findViewById(R.id.eventiTitolo);
        TextView eventiData = convertView.findViewById(R.id.eventiData);
        TextView eventiLuogo = convertView.findViewById(R.id.eventiLuogo);
        ImageButton eventiPrefHome = convertView.findViewById(R.id.iconaPreferitiHome);

        eventiPrefHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (isFilled) {
                   eventiPrefHome.setImageResource(R.drawable.ic_pref_not_filled);
                   Toast.makeText(context, "Evento rimosso dai preferiti", Toast.LENGTH_LONG).show();

               } else {
                   eventiPrefHome.setImageResource(R.drawable.ic_pref_filled);
                   Toast.makeText(context, "Evento aggiunto ai preferiti", Toast.LENGTH_LONG).show();

               }

               isFilled =! isFilled;
            }
        });


        Glide.with(context)
                .load(eventi.getUrlImage())
                .into(eventiImageView);

        eventiTitolo.setText(eventi.getTitolo());
        eventiData.setText(eventi.getData());
        eventiLuogo.setText(eventi.getLuogo());

        return convertView;
    }
}
