package com.android.damir.moviedb.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.ui.categories.paging.CategoryPagedListProvider
import com.android.damir.moviedb.ui.categories.paging.DataSourceFactory

class MovieByCategoryViewModel(
    id: Long
) : ViewModel() {

    private val factory = DataSourceFactory(id, viewModelScope)

    private val _movies = CategoryPagedListProvider(factory).provide()
    val movies: LiveData<PagedList<Movie>>
        get() = _movies

    fun refreshMovies() {
        factory.moviesLiveData.value?.invalidate()
    }
}