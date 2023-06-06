package com.example.cryptoinfo.feature.detalization;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cryptoinfo.data.models.CoinDetails;
import com.example.cryptoinfo.data.models.Coin;
import com.example.cryptoinfo.data.service.JsonPlaceHolderApi;
import com.example.cryptoinfo.databinding.CoinDeatilsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoinDetailsActivity extends AppCompatActivity {

    private CoinDeatilsBinding binding;
    private String apiUrl = "https://api.coincap.io/";

    private String dummyImage = "https://loremflickr.com/200/200/crypto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String coin = intent.getStringExtra("coin_name").toLowerCase();

        binding = CoinDeatilsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setTitle(coin);
        setSupportActionBar(binding.toolbar);

        //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //

        try {
            binding.imageView.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load(dummyImage)
                    // avoid caching to show different images each time
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(binding.imageView);
        } catch (Exception ex) {
            ex.printStackTrace();
            binding.imageView.setVisibility(View.GONE);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<CoinDetails> call = jsonPlaceHolderApi.getCoin(coin);

        call.enqueue(new Callback<CoinDetails>() {
            @Override
            public void onResponse(Call<CoinDetails> call, Response<CoinDetails> response) {
                if (!response.isSuccessful()) {
                    binding.textViewName.setText("Code: " + response.code());
                    return;
                }

                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().data != null) {
                        Coin coins = response.body().data;
//                        String content = "";
//                        content += "Name: " + coins.getName() + "\n";
//                        content += "Symbol: " + coins.getSymbol() + "\n";
//                        content += "Price: " + coins.getPriceUsd() + "\n";
                        binding.textViewName.setText("Name: " + coins.getName());
                        binding.textViewSymbol.setText("Symbol: " + coins.getSymbol());
                        binding.textViewPrice.setText("Price: " + coins.getPriceUsd());
//                        binding.textview.append(content);
                    }
                }

//                for (int i = 0; i < 15; i++) {
//                    String content = "";
//                    content += "Currency volume: " + coins.get(i).getCurrencyVolume() + "\n";
//                    content += "Symbol: " + coins.get(i).getSymbol() + "\n";
//                    content += "Currency: " + coins.get(i).getCurrency() + "\n";
//
//                    textView.append(content);
//                }
            }

            @Override
            public void onFailure(Call<CoinDetails> call, Throwable t) {
                t.getMessage();
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
