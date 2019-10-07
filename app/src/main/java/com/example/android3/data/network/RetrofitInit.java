package com.example.android3.data.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInit {
    private static final String BASE_URL = "https://api.github.com/";

    private static RetrofitProvider retrofitProvider;

    public static synchronized RetrofitProvider newApiInstance() {
        if (retrofitProvider == null) {
            retrofitProvider = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(RetrofitProvider.class);
        }
        return retrofitProvider;
    }

}
