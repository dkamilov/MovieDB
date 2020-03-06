package com.android.damir.moviedb.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.android.damir.moviedb.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.load(url: String?){
    Glide
        .with(this)
        .load("https://image.tmdb.org/t/p/w780$url")
        .placeholder(ColorDrawable(Color.GRAY))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}