package com.example.dogsappv2.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dogsappv2.R;
import com.example.dogsappv2.databinding.FragmentDetailBinding;
import com.example.dogsappv2.model.DogBreed;
import com.example.dogsappv2.util.Util;
import com.example.dogsappv2.viewmodel.DetailViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailFragment extends Fragment {
    // create some variable
    private int dogUuid;

    private DetailViewModel viewModel;
    private FragmentDetailBinding binding;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment by using DataBinding technology
        FragmentDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail,container, false);
            this.binding = binding;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      if (getArguments() != null){
          dogUuid = DetailFragmentArgs.fromBundle(getArguments()).getDogUuid();
      }
      viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
      viewModel.fetch(dogUuid);

        // call the observeViewModel
        observeViewModel();
    }
    private void observeViewModel(){
        viewModel.dogLiveData.observe(getViewLifecycleOwner(), dogBreed -> {
            if (dogBreed != null && dogBreed instanceof DogBreed && getContext() != null){
                binding.setDog(dogBreed);

            }

        });

    }


}