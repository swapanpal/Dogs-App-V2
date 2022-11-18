package com.example.dogsappv2.model;

import com.google.gson.annotations.SerializedName;

/**
 * This model class hold all variables of the API that need in app
 */
public class DogBreed {
    // public attribute that will come from API
    // use the same name of the variable that have in API, otherwise use below mention
    // @SerilizedName("") annotation

    @SerializedName("id")
    public String breedId;

    @SerializedName("name")
    public String dogBreed;
    @SerializedName("life_span")
    public String lifeSpan;

    @SerializedName("breed_group")
    public String breedGroup;

    @SerializedName("bred_for")
    public String bredFor;

    public String temperament;

    @SerializedName("url")
    public String imageUrl;

    // It is not as per API service, we will use it for database(Room Database
    public int uui;

    // Constructor for the above mentioned attribute of dog that will come from API
    public DogBreed(String breedId, String dogBreed, String lifeSpan, String breedGroup, String bredFor,
                    String temperament, String imageUrl) {
        this.breedId = breedId;
        this.dogBreed = dogBreed;
        this.lifeSpan = lifeSpan;
        this.breedGroup = breedGroup;
        this.bredFor = bredFor;
        this.temperament = temperament;
        this.imageUrl = imageUrl;
    }
}
