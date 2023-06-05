package com.example.cryptoinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptoinfo.databinding.CoinDeatilsBinding;
import com.example.cryptoinfo.databinding.FragmentFirstBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoinDetailsActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private CoinDeatilsBinding binding;
    private TextView textView;
    private String  apiUrl = "https://api.coincap.io/v2/assets/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        apiUrl += intent.getStringExtra("coin_name").toLowerCase() + "/";

        binding = CoinDeatilsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textView = binding.textview;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Root> call = jsonPlaceHolderApi.getCoin();

        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code: " + response.code());
                    return;
                }

                if(response.isSuccessful()) {
                    if(response.body()!=null && !response.body().data.isEmpty()) {
                        List<Coin> coins = response.body().data;
                        String content = "";
                        content += "Name: " + coins.get(0).getName() + "\n";
                        content += "Symbol: " + coins.get(0).getSymbol() + "\n";
                        content += "Price: " + coins.get(0).getPriceUsd() + "\n";

                        textView.append(content);
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
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
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
