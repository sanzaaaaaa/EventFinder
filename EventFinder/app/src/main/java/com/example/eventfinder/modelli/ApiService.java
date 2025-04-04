package com.example.eventfinder.modelli;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {
    @POST("/register")
    Call<Void> registerUser(@Body Utente user);

    @POST("/login")
    Call<Utente> loginUser(@Body Utente user);

    @GET("/get_users")
    Call<List<Utente>> getUsers();

    @POST("/aggiungi_preferiti")
    Call<Void> getEvents(@Query("utente_id") int idUtente, @Query("evento_id") int idEvento);

    @GET("/get_preferiti")
    Call<List<Utente>> getPreferiti();



}