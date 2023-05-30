package com.example.cryptoinfo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Coin {

    private double volume;

    private int latestTrade;

    private double weightedPrice;

    private double bid;

    private double high;

    private String currency;

    private double low;

    private double ask;

    private double close;

    private double avg;

    private String symbol;

    private int duration;

    private double currencyVolume;

    @SerializedName("body")
    private String text;



}
