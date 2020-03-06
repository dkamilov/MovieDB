package com.android.damir.moviedb.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieDetailsViewModelFactory(
    private val movieId: Long
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(movieId) as T
        }

        throw IllegalArgumentException("Could not instantiate " +
                MovieDetailsViewModel::class.java.simpleName)
    }
}