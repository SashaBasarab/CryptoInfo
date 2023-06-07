package com.example.cryptoinfo.data.service;

import static androidx.fragment.app.FragmentManager.TAG;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CustomInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        String responseBody = response.body().string();

        Log.d("TAG", responseBody);

        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), responseBody))
                .build();
    }
}
