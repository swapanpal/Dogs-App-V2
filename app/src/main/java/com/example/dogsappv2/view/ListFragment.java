package com.example.dogsappv2.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dogsappv2.R;
import com.example.dogsappv2.model.DogBreed;
import com.example.dogsappv2.viewmodel.ListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListFragment extends Fragment {
    private ListViewModel viewModel;
    private DogsListAdapter dogsListAdapter = new DogsListAdapter(new ArrayList<>());

    // Bind all view of fragment_list using ButterKnife
    @BindView(R.id.dogsList)
    RecyclerView dogsList;

    @BindView(R.id.listError)
    TextView listError;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Navigate the view direct from List to Detail (Direct open details fragment
        /**
         ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail();
         Navigation.findNavController(view).navigate(action);
         */

        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.refresh();

        // Now set adapter to RecyclerView
        dogsList.setLayoutManager(new LinearLayoutManager(getContext()));
        dogsList.setAdapter(dogsListAdapter);

        // call the observeViewModel method
        observeViewModel();
    }

    // Create a method to receive dogs information form ListViewModel (LiveData)
    private void observeViewModel(){
    viewModel.dogs.observe(getViewLifecycleOwner(), dogs -> {
        if (dogs != null && dogs instanceof List){
            dogsList.setVisibility(View.VISIBLE);
            dogsListAdapter.updateDogsList(dogs);
        }
    });

    viewModel.dogLoadError.observe(getViewLifecycleOwner(), isError -> {
        if (isError != null && isError instanceof Boolean){
            listError.setVisibility(isError ? View.VISIBLE : View.GONE);
        }
    });

    viewModel.loading.observe(getViewLifecycleOwner(), isLoading -> {
        if (isLoading != null && isLoading instanceof Boolean){
            loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if (isLoading){
                dogsList.setVisibility(View.GONE);
                listError.setVisibility(View.GONE);
            }
        }

    });
    }
}