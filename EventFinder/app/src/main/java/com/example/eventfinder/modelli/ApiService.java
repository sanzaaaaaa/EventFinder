package com.example.eventfinder.modelli;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import okhttp3.ResponseBody;


public interface ApiService {
    @POST("/register")
    Call<ResponseBody> registerUser(@Body utenti user);

    @POST("/login")
    Call<ResponseBody> loginUser(@Body utenti user);
}