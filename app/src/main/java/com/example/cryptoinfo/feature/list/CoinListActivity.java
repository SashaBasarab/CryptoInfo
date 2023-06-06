package com.example.cryptoinfo.feature.list;

import android.os.Bundle;

import com.example.cryptoinfo.data.models.CoinAdapter;
import com.example.cryptoinfo.data.models.CoinsList;
import com.example.cryptoinfo.data.models.Coin;
import com.example.cryptoinfo.data.service.CustomInterceptor;
import com.example.cryptoinfo.data.service.JsonPlaceHolderApi;
import com.example.cryptoinfo.databinding.CoinListBinding;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoinListActivity extends AppCompatActivity {

    private final String  apiUrl = "https://api.coincap.io/";

    private CoinListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = CoinListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // TODO: add toolbar and add surname & group
        binding.toolbar.setTitle("Sasha Basarab IPZs-21-2");
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CustomInterceptor customInterceptor = new CustomInterceptor();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(customInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<CoinsList> call = jsonPlaceHolderApi.getCoins();

        call.enqueue(new Callback<CoinsList>() {
            @Override
            public void onResponse(Call<CoinsList> call, Response<CoinsList> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(response.isSuccessful()) {
                    if(response.body()!=null && !response.body().data.isEmpty()) {
                        List<Coin> coins = response.body().data;
                        CoinAdapter adapter = new CoinAdapter(coins);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                         binding.recyclerView.setLayoutManager(layoutManager);
                         binding.recyclerView.setHasFixedSize(true);
                         binding.recyclerView.setAdapter(adapter);
                        adapter.setCoins(coins);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<CoinsList> call, Throwable t) {
                t.getMessage();
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}