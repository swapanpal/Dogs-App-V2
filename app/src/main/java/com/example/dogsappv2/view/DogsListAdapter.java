package com.example.dogsappv2.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogsappv2.R;
import com.example.dogsappv2.databinding.ItemDogBinding;
import com.example.dogsappv2.model.DogBreed;
import com.example.dogsappv2.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DogsListAdapter extends RecyclerView.Adapter<DogsListAdapter.DogViewHolder> {

    private ArrayList<DogBreed> dogsList;
// Constructor for the class
    public DogsListAdapter(ArrayList<DogBreed> dogsList) {
        this.dogsList = dogsList;
    }

    // Update the dogs list.
    public void updateDogsList(List<DogBreed> newDogsList){
        dogsList.clear();
        dogsList.addAll(newDogsList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemDogBinding view = DataBindingUtil.inflate(inflater,R.layout.item_dog,parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        // below mention one line of code do all without image load
        holder.itemView.setDog(dogsList.get(position));

//        ImageView image = holder.itemView.findViewById(R.id.imageView);
//        TextView name = holder.itemView.findViewById(R.id.name);
//        TextView lifespan = holder.itemView.findViewById(R.id.lifespan);
//        LinearLayout layout = holder.itemView.findViewById(R.id.dogLayout);
//
//        name.setText(dogsList.get(position).dogBreed);
//        lifespan.setText(dogsList.get(position).lifeSpan);
//
//        // Code the below line after creating Util class for Glide
//        // This line of code load image from API by using Glide library
//        Util.loadImage(image, dogsList.get(position).imageUrl, Util.getProgressDrawable(image.getContext()));
//
//        // set a clickListener to this view (new View.OnClickListener) to go detail
//        layout.setOnClickListener(view -> {
//      ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail();
//      action.setDogUuid(dogsList.get(position).uuid);
//            Navigation.findNavController(layout).navigate(action);
//        });

    }

    @Override
    public int getItemCount() {
        return dogsList.size();
    }

    public class DogViewHolder extends RecyclerView.ViewHolder {
        // ItemDogBinding class automatically generate after binding view at xml file(item_dog) and rebuild the project
        public ItemDogBinding itemView;
        public DogViewHolder(@NonNull ItemDogBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
