package com.example.cryptoinfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("v2/assets")
    Call<Root> getCoins();

}
