package com.example.cryptoinfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("v1/markets.json")
    Call<List<Coin>> getCoins();

}
