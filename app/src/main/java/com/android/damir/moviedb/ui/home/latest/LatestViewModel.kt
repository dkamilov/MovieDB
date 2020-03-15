package com.android.damir.moviedb.ui.home.latest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.ui.home.paging.DataSourceFactory
import com.android.damir.moviedb.ui.home.paging.MoviePagedListProvider
import com.android.damir.moviedb.utils.MOVIE_LATEST

class LatestViewModel : ViewModel() {

    private var factory = DataSourceFactory(MOVIE_LATEST, viewModelScope)
    
    private var _latest = MoviePagedListProvider(factory).provide()
    val latest: LiveData<PagedList<Movie>>
        get() = _latest

    fun refreshPopular(){
        factory.moviesLiveData.value?.invalidate()
    }
}