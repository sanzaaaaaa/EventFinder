package com.example.eventfinder.modelli;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventfinder.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registrati extends AppCompatActivity {
        private EditText registratiNomeEdit, registratiCognomeEdit, registratiGmailEdit, registratiNascitaEdit, registratiPwEdit;
        private Button registratiBtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registrati);

            registratiNomeEdit = findViewById(R.id.registratiNomeEdit);
            registratiCognomeEdit = findViewById(R.id.registratiCognomeEdit);
            registratiGmailEdit = findViewById(R.id.registratiGmailEdit);
            registratiNascitaEdit = findViewById(R.id.registratiNascitaEdit);
            registratiPwEdit = findViewById(R.id.registratiPwEdit);
            registratiBtn = findViewById(R.id.registratiBtn);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.118:5000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService apiService = retrofit.create(ApiService.class);

            registratiBtn.setOnClickListener(v -> {
                String nome = registratiNomeEdit.getText().toString();
                String cognome = registratiCognomeEdit.getText().toString();
                String email = registratiGmailEdit.getText().toString();
                String dataNascita = registratiNascitaEdit.getText().toString();
                String password = registratiPwEdit.getText().toString();

                utenti user = new utenti(nome, cognome, email, dataNascita, password);
                Call<ResponseBody> call = apiService.registerUser(user);

                call.enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(Registrati.this, "Registrazione completata!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(Registrati.this, "Errore nella registrazione!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(Registrati.this, "Errore di connessione!", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        }
    }