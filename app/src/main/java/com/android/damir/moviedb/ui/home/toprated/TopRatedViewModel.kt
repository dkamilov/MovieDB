package com.android.damir.moviedb.ui.home.toprated

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.ui.home.paging.DataSourceFactory
import com.android.damir.moviedb.ui.home.paging.MoviePagedListProvider
import com.android.damir.moviedb.utils.MOVIE_TOP_RATED

class TopRatedViewModel : ViewModel(){

    private var factory = DataSourceFactory(MOVIE_TOP_RATED, viewModelScope)

    private var _topRated = MoviePagedListProvider(factory).provide()
    val topRated: LiveData<PagedList<Movie>>
        get() = _topRated

    fun refreshPopular(){
        factory.moviesLiveData.value?.invalidate()
    }
}