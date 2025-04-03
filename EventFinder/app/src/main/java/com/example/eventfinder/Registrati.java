package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.example.eventfinder.modelli.ApiService;
import com.example.eventfinder.modelli.RetrofitClient;
import com.example.eventfinder.modelli.SharedPreference;
import com.example.eventfinder.modelli.Utente;

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

        registratiBtn.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String n = nome.getText().toString();
        String c = cognome.getText().toString();
        String e = email.getText().toString();
        String d = data_di_nascita.getText().toString();
        String p = password.getText().toString();

        if (n.isEmpty() || c.isEmpty() || e.isEmpty() || d.isEmpty() || p.isEmpty()) {
            Toast.makeText(Registrati.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Utente nuovoUtente = new Utente(n, c, e, d, p);

        Call<Void> call = apiService.registerUser(nuovoUtente);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    SharedPreference sharedPreference = new SharedPreference(Registrati.this);
                    sharedPreference.saveNome(n);
                    sharedPreference.saveCognome(c);
                    sharedPreference.saveEmail(e);
                    sharedPreference.saveDataDiNascita(d);
                    sharedPreference.setLoggedIn(false);

                    // Messaggio di successo
                    Toast.makeText(Registrati.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                    // Reindirizza alla schermata di login
                    Intent intent = new Intent(Registrati.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Registrati.this, "Registration failed. Try again later.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Registrati.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
