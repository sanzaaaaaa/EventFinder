package com.example.eventfinder.modelli;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eventfinder.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class EventiAdapter extends ArrayAdapter<Eventi> {
    private Context context;
    private List<Eventi> eventiList;
    private boolean isFilled = false;

    public EventiAdapter(Context context, List<Eventi> eventiList) {
        super(context, 0, eventiList);
        this.context = context;
        this.eventiList = eventiList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lista_eventi, parent, false);
        }

        Eventi eventi = eventiList.get(position);
        ImageView eventiImageView = convertView.findViewById(R.id.eventiImageView);
        TextView eventiTitolo = convertView.findViewById(R.id.eventiTitolo);
        TextView eventiData = convertView.findViewById(R.id.eventiData);
        TextView eventiLuogo = convertView.findViewById(R.id.eventiLuogo);




        Glide.with(context)
                .load(eventi.getUrlImage())
                .into(eventiImageView);

        eventiTitolo.setText(eventi.getTitolo());
        eventiData.setText(eventi.getData());
        eventiLuogo.setText(eventi.getLuogo());

        return convertView;
    }

}
