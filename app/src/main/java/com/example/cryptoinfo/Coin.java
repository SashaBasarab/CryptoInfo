package com.example.cryptoinfo;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Coin {

    @SerializedName("id")
    private String id;

    @SerializedName("rank")
    private String rank;

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("name")
    private String name;

    @SerializedName("supply")
    private String supply;

    @SerializedName("maxSupply")
    private String maxSupply;

    @SerializedName("marketCapUsd")
    private String marketCapUsd;

    @SerializedName("volumeUsd24Hr")
    private String volumeUsd24Hr;

    @SerializedName("priceUsd")
    private String priceUsd;

    @SerializedName("changePercent24Hr")
    private String changePercent24Hr;

    @SerializedName("vwap24Hr")
    private String vwap24Hr;

    @SerializedName("explorer")
    private String explorer;

}




