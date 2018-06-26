package com.pic.hub.pichub.api;

import com.pic.hub.pichub.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit INSTANCE = null;

    public static Retrofit getRetrofitInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }
}
