package com.example.eventfinder.modelli;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {
    @POST("/mobile_register")
    Call<Void> registerUser(@Body Utente user);

    @POST("/mobile_login")
    Call<Utente> loginUser(@Body Utente user);

    @GET("/get_users")
    Call<List<Utente>> getUsers();

    @GET("/get_eventi")
    Call<List<Eventi>> getEventi();

    @POST("/aggiungi_preferiti")
    Call<Void> getEvents(@Body EventiPreferiti preferiti);


    @GET("/get_preferiti")
    Call<List<Eventi>> getPreferiti(@Query("utente_id") int idUtente);



}