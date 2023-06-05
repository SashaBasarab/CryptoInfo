package com.example.cryptoinfo;

import android.os.Bundle;

import com.example.cryptoinfo.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptoinfo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private FragmentFirstBinding binding;
    private TextView textView;
    private RecyclerView recyclerView;
    private final String  apiUrl = "https://api.coincap.io/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentFirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        textView = binding.textviewFirst;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Root> call = jsonPlaceHolderApi.getCoins();

        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
//                if (!response.isSuccessful()) {
//                    textView.setText("Code: " + response.code());
//                    return;
//                }

                if(response.isSuccessful()) {
                    if(response.body()!=null && !response.body().data.isEmpty()) {
                        List<Coin> coins = response.body().data;
                        CoinAdapter adapter = new CoinAdapter(coins);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adapter);
                        adapter.setCoins(coins);
                        adapter.notifyDataSetChanged();
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
            public void onFailure(Call<Root> call, Throwable t) {
//                textView.setText(t.getMessage());
                t.getMessage();
                Toast.makeText(getApplicationContext(), "Ваше повідомлення", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}