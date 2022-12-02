package com.example.dogsappv2.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dogsappv2.model.DogBreed;
import com.example.dogsappv2.model.DogDao;
import com.example.dogsappv2.model.DogDatabase;
import com.example.dogsappv2.model.DogsApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

// If we did not need to use Contex than use only "ViewModel" otherwise use "AndroidViewModel"
// One ViewModel can display only one view, example "ListFragment", If we want to display another view than
// replace it with existing view.
public class ListViewModel extends AndroidViewModel {

    // Create some variable of MutableLiveData with data type, example "<List<DogBreed>>"
    // MutableLiveData means it is changeable of it's value.
    public MutableLiveData<List<DogBreed>> dogs = new MutableLiveData<List<DogBreed>>();
    public MutableLiveData<Boolean> dogLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    // Create a variable of DogsApiService
    private DogsApiService dogsService = new DogsApiService();

    // collect disposable observer
    private CompositeDisposable disposable = new CompositeDisposable();

    // Create a instance of AsyncTask to use in any time
    private AsyncTask<List<DogBreed>, Void, List<DogBreed>> insertTask;

    // Default constructor of the AndroidViewModel
    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * This method refresh/Update the data we found from API but currently we use fake data
     */
    public void refresh() {
        fetchFromRemote();

    }

    private void fetchFromRemote() {
        loading.setValue(true);
        disposable.add(
                dogsService.getDogs()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                            @Override
                            public void onSuccess(List<DogBreed> dogBreeds) {
                               insertTask = new insertDogsTask();
                               insertTask.execute(dogBreeds);
                                Toast.makeText(getApplication(), "Dogs retrived from endpoint", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                dogLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );

    }
    // This method is called when data stored in local database
    // This method will call on main thread
    private void dogsRetrived(List<DogBreed> dogList){
        dogs.setValue(dogList);
        dogLoadError.setValue(false);
        loading.setValue(false);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();

        // if any task is running than cancel it and set value to null.
        if (insertTask != null){
            insertTask.cancel(true);
            insertTask = null;
        }
    }

    /**
     * Create class for AsyncTask (Background thread)
     */
    private class insertDogsTask extends AsyncTask<List<DogBreed>, Void, List<DogBreed>>{

        @Override
        protected List<DogBreed> doInBackground(List<DogBreed>... lists) {
            List<DogBreed> list = lists[0];
            DogDao dao = DogDatabase.getInstance(getApplication()).dogDao();
            dao.deleteAllDogs();  // First delete all dogs because we wants to create a fresh database

            ArrayList<DogBreed> newList = new ArrayList<>(list);
            List<Long> result = dao.insertAll(newList.toArray(new DogBreed[0]));

            // create a while loop to get all dogs from the list
            int i = 0;
            while (i < list.size()){
                list.get(i).uuid = result.get(i).intValue();
                i++;
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<DogBreed> dogBreeds) {
            dogsRetrived(dogBreeds);
        }
    }
}
