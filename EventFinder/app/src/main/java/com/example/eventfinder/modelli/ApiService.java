package com.example.eventfinder.modelli;

import com.example.eventfinder.Biglietti;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

    @POST("/rimuovi_preferiti")
    Call<Void> deleteEvents(@Body EventiPreferiti preferiti);


    @GET("/get_preferiti/{utente_id}")
    Call<List<Eventi>> getPreferiti(@Path("utente_id") int idUtente);

    @POST("/aggiungi_biglietti")
    Call<List<Void>> postBiglietti(@Body EventiBiglietti biglietti);

    @GET("/get_biglietti/{utente_id}")
    Call<List<Eventi>> getBiglietti(@Path("utente_id") int idUtente);



}