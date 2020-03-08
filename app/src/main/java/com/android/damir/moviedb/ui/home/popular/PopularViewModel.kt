package com.android.damir.moviedb.ui.home.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.ui.home.paging.DataSourceFactory
import com.android.damir.moviedb.ui.home.paging.MoviePagedListProvider
import com.android.damir.moviedb.utils.MOVIE_POPULAR

class PopularViewModel : ViewModel(){

    private var factory = DataSourceFactory(MOVIE_POPULAR, viewModelScope)

    private var _popular = MoviePagedListProvider(factory).provide()
    val popular: LiveData<PagedList<Movie>>
        get() = _popular

    fun refreshPopular(){
        factory.moviesLiveData.value?.invalidate()
    }
}