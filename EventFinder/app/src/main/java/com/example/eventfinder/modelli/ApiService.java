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

    @GET("/aggiungi_preferiti")
    Call<Void> getEvents(@Query("id_utente") int idUtente, @Query("id_evento") int idEvento);


}