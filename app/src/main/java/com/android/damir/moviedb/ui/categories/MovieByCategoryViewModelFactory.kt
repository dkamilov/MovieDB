package com.android.damir.moviedb.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieByCategoryViewModelFactory(
    private val id: Long
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(MovieByCategoryViewModel::class.java)) {
            return MovieByCategoryViewModel(id) as T
        }

        throw IllegalArgumentException("Could not instantiate " +
                MovieByCategoryViewModel::class.java.simpleName)
    }
}