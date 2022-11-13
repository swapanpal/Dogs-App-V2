package com.example.dogsappv2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dogsappv2.model.DogBreed;

import java.util.ArrayList;
import java.util.List;
// If we did not need to use Contex than use only "ViewModel" otherwise use "AndroidViewModel"
// One ViewModel can display only one view, example "ListFragment", If we want to display another view than
// replace it with existing view.
public class ListViewModel extends AndroidViewModel {

    // Create some variable of MutableLiveData with data type, example "<List<DogBreed>>"
    // MutableLiveData means it is changeable of it's value.
    public MutableLiveData<List<DogBreed>> dogs = new MutableLiveData<List<DogBreed>>();
    public MutableLiveData<Boolean> dogLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    // Default constructor of the AndroidViewModel
    public ListViewModel(@NonNull Application application) {
        super(application);
    }
    /**
     * This method refresh/Update the data we found from API but currently we use fake data
     */
    public void refresh(){
        DogBreed dog1 = new DogBreed("1", "corgi", "15 years",
                "","","","");
        DogBreed dog2 = new DogBreed("2", "Rotwallar", "13 years",
                "","","","");
        DogBreed dog3 = new DogBreed("3", "Labridor", "10 years",
                "","","","");
        DogBreed dog4 = new DogBreed("1", "corgi", "15 years",
                "","","","");
        DogBreed dog5 = new DogBreed("2", "Rotwallar", "13 years",
                "","","","");
        DogBreed dog6 = new DogBreed("3", "Labridor", "10 years",
                "","","","");
        DogBreed dog7 = new DogBreed("1", "corgi", "15 years",
                "","","","");
        DogBreed dog8 = new DogBreed("2", "Rotwallar", "13 years",
                "","","","");
        DogBreed dog9 = new DogBreed("3", "Labridor", "10 years",
                "","","","");

        // Create a ArrayList to add all data to it.
        ArrayList<DogBreed> dogsList = new ArrayList<>();
        dogsList.add(dog1);
        dogsList.add(dog2);
        dogsList.add(dog3);
        dogsList.add(dog4);
        dogsList.add(dog5);
        dogsList.add(dog6);
        dogsList.add(dog7);
        dogsList.add(dog8);
        dogsList.add(dog9);

        // Now set value to all MutableLiveData.
        dogs.setValue(dogsList);
        dogLoadError.setValue(false);
        loading.setValue(false);
    }
}
