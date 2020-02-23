package com.android.damir.thousandmovie.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(url: String?){
    Glide
        .with(this)
        .load("https://image.tmdb.org/t/p/w780$url")
        .into(this)
}