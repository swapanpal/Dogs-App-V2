package com.example.dogsappv2.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * This interface hold the endpoint of the API
 */
public interface DogsApi {
    // Annoted the endpoint of API url (There are two part in API url, 1. host and 2. endpoing
    // Full API URL: https://raw.githubusercontent.com/DevTides/DogsApi/master/dogs.json
    @GET("DevTides/DogsApi/master/dogs.json")
    Single<List<DogBreed>> getDogs();  // Single is a RxJava class that is Observable and it is just a single unit.
}
