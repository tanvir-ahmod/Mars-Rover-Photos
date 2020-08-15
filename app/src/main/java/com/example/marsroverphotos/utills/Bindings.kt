package com.example.marsroverphotos.utills

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: AppCompatImageView, url: Int){
    Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("imageUrl")
fun loadImageUrl(view: AppCompatImageView, url: String){
    Glide.with(view.context).load(url).into(view)
}