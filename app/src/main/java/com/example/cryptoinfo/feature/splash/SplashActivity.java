package com.example.cryptoinfo.feature.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cryptoinfo.R;
import com.example.cryptoinfo.databinding.SplashScreenBinding;
import com.example.cryptoinfo.feature.list.CoinListActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2000;

    private SplashScreenBinding splashScreenBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, CoinListActivity.class);
                startActivity(intent);

                finish();
            }
        }, SPLASH_DELAY);
    }
}
