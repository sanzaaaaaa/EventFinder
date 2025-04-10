package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;


import com.example.eventfinder.modelli.ApiService;
import com.example.eventfinder.modelli.Eventi;
import com.example.eventfinder.modelli.EventiAdapter;
import com.example.eventfinder.modelli.RetrofitClient;
import com.example.eventfinder.modelli.SharedPreference;



import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private ListView listView;
    private List<Eventi> eventiList = new ArrayList<>();
    private List<Eventi> eventiListFiltered;
    private EventiAdapter eventiAdapter;
    private ApiService eventoApi;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

        SharedPreference sharedPreference = new SharedPreference(this);

        SearchView cerca = findViewById(R.id.searchView);
        Button loginButtonHome = findViewById(R.id.loginHomeBtn);
        ImageButton btnFiltro = findViewById(R.id.btnFiltro);
        listView = findViewById(R.id.eventiListView);
        ImageButton btnBiglietto = findViewById(R.id.btnBiglietti);
        ImageButton btnPreferiti = findViewById(R.id.btnPrefe);
        ImageButton btnAmici = findViewById(R.id.btnHomeAmici);
        ImageButton btnProfilo = findViewById(R.id.btnProfilo);


        TextView ciao = findViewById(R.id.benvenuto);
        TextView slogan1 = findViewById(R.id.slogan);

        eventiListFiltered = new ArrayList<>(eventiList);
        eventiAdapter = new EventiAdapter(this, eventiListFiltered);
        listView.setAdapter(eventiAdapter);


        if (sharedPreference.isLoggedIn()) {
            loginButtonHome.setVisibility(View.INVISIBLE);
            ciao.setVisibility(View.VISIBLE);
            slogan1.setVisibility(View.VISIBLE);
            String nome = sharedPreference.getNome();
            ciao.setText("Benvenuto "+ nome);

        } else  {
            loginButtonHome.setVisibility(View.VISIBLE);
            ciao.setVisibility(View.INVISIBLE);
            slogan1.setVisibility(View.INVISIBLE);
        }

        btnBiglietto.setOnClickListener(v -> {
            Intent biglietto = new Intent(HomeActivity.this, Biglietti.class);
            startActivity(biglietto);
        });

        btnPreferiti.setOnClickListener(v -> {
            Intent preferiti = new Intent(HomeActivity.this, Preferiti.class);
            startActivity(preferiti);

        });


        btnAmici.setOnClickListener(v -> {
            Intent amici = new Intent(HomeActivity.this, AmiciActivity.class);
            startActivity(amici);
        });

        btnProfilo.setOnClickListener(v -> {
            if(sharedPreference.isLoggedIn()){
            Intent profilo = new Intent(HomeActivity.this, Profilo.class);
            startActivity(profilo);
            }else{
                Intent login = new Intent(HomeActivity.this, Login.class);
                startActivity(login);
            }
        });

        btnFiltro.setOnClickListener(v -> {
            Intent filtro = new Intent(HomeActivity.this, Filtri.class);
            startActivityForResult(filtro, 1);

        });

        loginButtonHome.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, Login.class);
            startActivity(intent);
        });





        eventoApi = RetrofitClient.getApiService().create(ApiService.class);
        Call<List<Eventi>> call = eventoApi.getEventi();
        call.enqueue(new Callback<List<Eventi>>() {
            @Override
            public void onResponse(Call<List<Eventi>> call, Response<List<Eventi>> response) {
                eventiList.clear();
                eventiList.addAll(response.body());
                eventiAdapter.notifyDataSetChanged();
                eventiListFiltered.clear();
                eventiListFiltered.addAll(eventiList);
                Log.d("EVENTI", "Ricevuti: " + response.body().size());

            }

            @Override
            public void onFailure(Call<List<Eventi>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Errore nel caricamento dei dati", Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent infoIntent = new Intent(this, InfoEventi.class);
            infoIntent.putExtra("evento_id", eventiList.get(position).getId());
            infoIntent.putExtra("titolo", eventiList.get(position).getTitolo());
            infoIntent.putExtra("data", eventiList.get(position).getData());
            infoIntent.putExtra("urlImage", eventiList.get(position).getUrlImage());
            infoIntent.putExtra("info_evento", eventiList.get(position).getInfo_evento());
            infoIntent.putExtra("info_artista", eventiList.get(position).getInfo_artista());
            infoIntent.putExtra("prezzo", eventiList.get(position).getPrezzo());
            infoIntent.putExtra("luogo", eventiList.get(position).getLuogo());
            startActivity(infoIntent);
        });

        cerca.setOnClickListener(v -> {
            if (!cerca.isIconified()) {
                return;
            }
            cerca.setIconified(false);
        });

        cerca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String categoria = data.getStringExtra("filtro_categoria");
            int prezzoMax = data.getIntExtra("filtro_prezzo", 0);
            String luogo = data.getStringExtra("filtro_luogo");
            String dataEvento = data.getStringExtra("filtro_data");
            String artista = data.getStringExtra("filtro_artista");

            if (categoria == null) categoria = "";
            if (luogo == null) luogo = "";
            if (dataEvento == null) dataEvento = "";
            if (artista == null) artista = "";

            applicaFiltri(categoria, artista, prezzoMax, luogo, dataEvento);

        }
    }

    private void applicaFiltri(String categoria, String artista, int prezzoMax, String luogo, String dataEvento) {
        eventiListFiltered.clear();

        for (Eventi evento : eventiList) {
            boolean matchPrezzo = true;
            try {
                matchPrezzo = Integer.parseInt(evento.getPrezzo()) <= prezzoMax;
            } catch (NumberFormatException e) {
                matchPrezzo = false;
            }
            boolean matchLuogo = evento.getLuogo().toLowerCase().contains(luogo.toLowerCase());
            boolean matchInfo_artista = evento.getInfo_artista().toLowerCase().contains(artista.toLowerCase());
            boolean matchData = evento.getData().startsWith(dataEvento);

            if (matchPrezzo && matchLuogo && matchData && matchInfo_artista ) {
                eventiListFiltered.add(evento);
            }
        }

        eventiAdapter.notifyDataSetChanged();

        if (eventiListFiltered.isEmpty()) {
            Toast.makeText(this, "Nessun evento trovato con questi filtri", Toast.LENGTH_SHORT).show();
        }
    }


    private void filterList(String query) {
        eventiListFiltered.clear();

        if (query.isEmpty()) {
            eventiListFiltered.addAll(eventiList);
        } else {
            for (Eventi evento : eventiList) {
                if (evento.getTitolo().toLowerCase().contains(query.toLowerCase())) {
                    eventiListFiltered.add(evento);
                }
            }
        }
        eventiAdapter.notifyDataSetChanged();
    }
}