package com.android.damir.thousandmovie.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.android.damir.thousandmovie.domain.entity.Movie
import com.android.damir.thousandmovie.movielist.pagination.DataSourceFactory
import com.android.damir.thousandmovie.movielist.pagination.MoviePagedListProvider
import com.android.damir.thousandmovie.movielist.pagination.isRefreshing

class MovieListViewModel : ViewModel(){

    private var factory = DataSourceFactory(viewModelScope)

    private var _popularPagedListLiveData = MoviePagedListProvider(factory).provide()
    val popularPagedListLiveData: LiveData<PagedList<Movie>>
        get() = _popularPagedListLiveData

    private var _isRefreshingLiveData = MutableLiveData<Boolean>()
    val isRefreshingLiveData: LiveData<Boolean>
        get() = _isRefreshingLiveData


    fun refreshPopular(){
        factory = DataSourceFactory(viewModelScope)
        _popularPagedListLiveData = MoviePagedListProvider(factory).provide()
        _isRefreshingLiveData.value = isRefreshing
    }
}