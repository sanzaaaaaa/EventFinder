package com.example.eventfinder.modelli;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {
    @POST("/register")
    Call<Void> registerUser(@Body Utenti user);

    @POST("/login")
    Call<Void> loginUser(@Body Utenti user);

}