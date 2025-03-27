package com.example.eventfinder.modelli;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {
    @POST("/register")
    Call<Void> registerUser(@Body Utente user);

    @POST("/login")
    Call<Void> loginUser(@Body Utente user);

    @GET("/get_users")
    Call<List<Utente>> getUsers();

}