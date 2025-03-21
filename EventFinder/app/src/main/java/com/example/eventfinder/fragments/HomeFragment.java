package com.example.eventfinder.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eventfinder.InfoEventiLp;
import com.example.eventfinder.Login;
import com.example.eventfinder.R;
import com.example.eventfinder.modelli.Eventi;
import com.example.eventfinder.modelli.EventiAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ListView listView;
    List<Eventi> eventiList;
    private EventiAdapter eventiAdapter;

    Context ctx = null;

    public HomeFragment(Context ctx) {
        this.ctx = ctx;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {




        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        Button loginButtonHome = rootView.findViewById(R.id.loginHomeBtn);
        loginButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, Login.class);
                startActivity(intent);
            }
        });

        listView = rootView.findViewById(R.id.eventiListView);


        eventiList = new ArrayList<Eventi>();
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/galery/222x222/l/linkin-park-biglietti.jpg", "Linkin Park",
                "mar 24 giugno, 16:00", "Ippodromo SNAI La Maura"));
        eventiList.add(new Eventi("https://www.ticketone.it/obj/media/IT-eventim/teaser/222x222/2024/nazzi-nuova-storia-biglietti.jpg", "Stefano Nazzi - Indagini Live - Una nuova storia",
                "gio 03 Aprile, 21:01", "Teatro Arcimboldi"));

        eventiAdapter = new EventiAdapter(ctx, eventiList);
        listView.setAdapter(eventiAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ctx, "tu sei: " + eventiList.get(position), Toast.LENGTH_LONG).show();

                Intent infoIntent = new Intent(ctx, InfoEventiLp.class);
                infoIntent.putExtra("luogo", eventiList.get(position).getLuogo());
                startActivity(infoIntent);
            }
        });


        return rootView;
    }
}
