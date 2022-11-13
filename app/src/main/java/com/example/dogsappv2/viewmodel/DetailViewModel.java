package com.example.dogsappv2.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dogsappv2.model.DogBreed;

/**
 *If we did not need to use Context than use only "ViewModel" otherwise use "AndroidViewModel"
 *One ViewModel can display only one view, example "ListFragment", If we want to display another view than
 *replace it with existing view.
 */
public class DetailViewModel extends ViewModel {

    // Create some variable of MutableLiveData with data type, example "<List<DogBreed>>"
    // MutableLiveData means it is changeable of it's value.
    public MutableLiveData<DogBreed> dogLiveData = new MutableLiveData<DogBreed>();

    /**
     * This method will fetch the data from database(Room database) but currently we use fake data
     */
    public void fetch(){
        DogBreed dog1 = new DogBreed("1", "corgi", "15 years",
                "","Companionship","Calm and friendly","");

        dogLiveData.setValue(dog1);
    }
}
