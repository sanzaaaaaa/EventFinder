package com.example.eventfinder.modelli;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eventfinder.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registrati extends AppCompatActivity {
    private EditText nome, cognome, email, data_di_nascita, password;
    private Button registratiBtn;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrati);

        nome = findViewById(R.id.registratiNomeEdit);
        cognome = findViewById(R.id.registratiCognomeEdit);
        email = findViewById(R.id.registratiGmailEdit);
        data_di_nascita = findViewById(R.id.registratiNascitaEdit);
        password = findViewById(R.id.registratiPwEdit);

        registratiBtn = findViewById(R.id.registratiBtn);

        apiService = RetrofitClient.getApiService().create(ApiService.class);

        registratiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }

        });

    }
    private void registerUser() {
        String n = nome.getText().toString();
        String c = cognome.getText().toString();
        String e = email.getText().toString();
        String d = data_di_nascita.getText().toString();
        String p = password.getText().toString();

        Utenti nuovoUtente = new Utenti(n,c,e,d,p);
        Call<Void> call = apiService.registerUser(nuovoUtente);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                Toast.makeText(Registrati.this,"Registration Successful!",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Registrati.this,"Something went wrong...",Toast.LENGTH_SHORT).show();

            }
        });

    }
}