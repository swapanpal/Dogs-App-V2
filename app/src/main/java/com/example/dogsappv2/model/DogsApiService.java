package com.example.dogsappv2.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This Class communicate with API by using Retrofit and GET and POST data
 */
public class DogsApiService {

    private static final String BASE_URL = "https://raw.githubusercontent.com/";

    private DogsApi api;
    // public constructor of the class
    public DogsApiService(){
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(DogsApi.class);
    }

    // method that return a list of dog information
    public Single<List<DogBreed>> getDogs(){
        return api.getDogs();
    }
}
