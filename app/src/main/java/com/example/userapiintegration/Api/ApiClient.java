package com.example.userapiintegration.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit RETROFIT = null;

    public static Retrofit getRETROFIT() {
        if (RETROFIT==null){
            Gson gson=new GsonBuilder().create();
            OkHttpClient client=new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60,TimeUnit.SECONDS)
                    .readTimeout(60,TimeUnit.SECONDS)
                    .build();

            RETROFIT=new Retrofit.Builder()
                    .baseUrl("http:///userApi/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return RETROFIT;
    }
}
