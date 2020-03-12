package com.android.damir.moviedb.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
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

fun EditText.showKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun FragmentManager.showMovieByCategory(container: Int, fragment: Fragment) {
    beginTransaction()
        .add(container, fragment, TAG_MOVIE_BY_CATEGORY)
        .hide(findFragmentByTag(TAG_CATEGORIES)!!)
        .addToBackStack(null)
        .commit()
}