package com.example.cryptoinfo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Coin {

    @SerializedName("volume")
    private double volume;

    @SerializedName("latestTrade")
    private int latestTrade;

    @SerializedName("weightedPrice")
    private double weightedPrice;

    @SerializedName("bid")
    private double bid;

    @SerializedName("high")
    private double high;

    @SerializedName("currency")
    private String currency;

    @SerializedName("low")
    private double low;

    @SerializedName("ask")
    private double ask;

    @SerializedName("close")
    private double close;

    @SerializedName("avg")
    private double avg;

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("duration")
    private int duration;

    @SerializedName("currencyVolume")
    private double currencyVolume;

    @SerializedName("body")
    private String text;



}
