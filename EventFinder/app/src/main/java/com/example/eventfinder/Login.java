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

public class Login extends AppCompatActivity {

    private EditText email, password;
    private Button loginBtn;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

        SharedPreference sharedPreference = new SharedPreference(this);

        Button registratiButton = findViewById(R.id.registrati);
        email = findViewById(R.id.emailLogineditText);
        password = findViewById(R.id.pswLogineditText);

        loginBtn = findViewById(R.id.loginBtn);

        apiService = RetrofitClient.getApiService().create(ApiService.class);

        registratiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registrati.class);
                startActivity(intent);
            }

        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
                sharedPreference.setLoggedIn(true);
            }

        });

    }
    private void loginUser() {
        String e = email.getText().toString();
        String p = password.getText().toString();
        if (e.isEmpty()) {
            Toast.makeText(Login.this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (p.isEmpty()) {
            Toast.makeText(Login.this, "Please enter password and email", Toast.LENGTH_SHORT).show();
            return;
        }


        Utente nuovoUtente = new Utente(e, p);

        Call<Void> call = apiService.loginUser(nuovoUtente);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, HomeActivity.class);  // Change HomeActivity to your main screen activity
                    startActivity(intent);
                    finish();
                } else if (response.code() == 401) {
                    Toast.makeText(Login.this, "password sbagliata", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 404) {
                    Toast.makeText(Login.this, "Email non registrata", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Login.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

