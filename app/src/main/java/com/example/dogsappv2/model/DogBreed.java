package com.example.dogsappv2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * This model class hold all variables of the API that need in app
 */
@Entity
public class DogBreed {
    // public attribute that will come from API
    // use the same name of the variable that have in API, otherwise use below mention
    // @SerilizedName("") annotation
    // Use @ColumnInfo(name=") annotation if string name is not same as API info otherwise not

    @ColumnInfo(name = "breed_id")
    @SerializedName("id")
    public String breedId;

    @ColumnInfo(name = "dog_name")
    @SerializedName("name")
    public String dogBreed;

    @ColumnInfo(name = "life_span")
    @SerializedName("life_span")
    public String lifeSpan;

    @ColumnInfo(name = "breed_group")
    @SerializedName("breed_group")
    public String breedGroup;

    @ColumnInfo(name = "fred_for")
    @SerializedName("bred_for")
    public String bredFor;

    public String temperament;      // No need to annotation because we used same name as API

    @ColumnInfo(name = "dog_url")
    @SerializedName("url")
    public String imageUrl;

    // It is not as per API service, we will use it for database(Room Database
    @PrimaryKey(autoGenerate = true)
    public int uuid;

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
    // Create a empty constructor
    public DogBreed() {
    }
}
