package com.example.dogsappv2.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dogsappv2.model.DogBreed;
import com.example.dogsappv2.model.DogDatabase;

/**
 *If we did not need to use Context than use only "ViewModel" otherwise use "AndroidViewModel"
 *One ViewModel can display only one view, example "ListFragment", If we want to display another view than
 *replace it with existing view.
 */
public class DetailViewModel extends AndroidViewModel {

    // Create some variable of MutableLiveData with data type, example "<List<DogBreed>>"
    // MutableLiveData means it is changeable of it's value.
    public MutableLiveData<DogBreed> dogLiveData = new MutableLiveData<DogBreed>();
    private RetrieveDogTask task;

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * This method will fetch the data from database(Room database) but currently we use fake data
     */
    public void fetch(int uuid){
        // now instantiate the task
        task = new RetrieveDogTask();
        task.execute(uuid);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (task != null){
            task.cancel(true);
            task = null;
        }
    }

    /**
     * Create class for AsyncTask (Background thread)
     * This class for retrieve data from database
     */
    private class RetrieveDogTask extends AsyncTask<Integer, Void, DogBreed>{

        @Override
        protected DogBreed doInBackground(Integer... integers) {
            int uuid = integers[0];
            return DogDatabase.getInstance(getApplication()).dogDao().getDog(uuid);
        }

        // Here we will update our liveData.
        @Override
        protected void onPostExecute(DogBreed dogBreed) {
            dogLiveData.setValue(dogBreed);
        }
    }
}
