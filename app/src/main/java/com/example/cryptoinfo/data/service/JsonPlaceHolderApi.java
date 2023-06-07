package com.example.cryptoinfo.data.service;

import com.example.cryptoinfo.data.models.CoinDetails;
import com.example.cryptoinfo.data.models.CoinsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("v2/assets")
    Call<CoinsList> getCoins();

    @GET("v2/assets/{coin}")
    Call<CoinDetails> getCoin(@Path("coin") String coin);

}
