package com.example.dogsappv2.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dogsappv2.R;

public class Util {
    public static void loadImage(ImageView imageView, String url, CircularProgressDrawable progressDrawable){
        //We can set optional Request option
        RequestOptions options = new RequestOptions()
                .placeholder(progressDrawable)
                .error(R.mipmap.ic_launcher);

        // using Glide
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(options)  // we can omit this option
                .load(url)
                .into(imageView);
    }

    // Create a static method for loading time progress spinner's size and style
    public static CircularProgressDrawable getProgressDrawable(Context context){
        CircularProgressDrawable cpd = new CircularProgressDrawable(context);
                cpd.setStrokeWidth(10f);
                cpd.setCenterRadius(50f);
                cpd.start();
                return cpd;
    }
    // Create a imageUrl utility that we used in item_dog.xml file to bind the image
    @BindingAdapter("android:imageUrl")
    public static void loadImage(ImageView view, String url){
        // this method call will automatically load the image
        loadImage(view, url, getProgressDrawable(view.getContext()));

    }
}
