package com.android.damir.thousandmovie.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.android.damir.thousandmovie.domain.entity.Movie
import com.android.damir.thousandmovie.domain.repository.MovieRepository
import com.android.damir.thousandmovie.domain.repository.MovieRepositoryImpl
import com.android.damir.thousandmovie.movielist.pagination.MoviePageDataSource
import com.android.damir.thousandmovie.movielist.pagination.MoviePagedListProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.sql.DataSource

class MovieListViewModel : ViewModel(){

    private val _isRefreshingLiveData = MutableLiveData<Boolean>()
    val isRefreshingLiveData: LiveData<Boolean>
        get() = _isRefreshingLiveData

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean>
        get() = _isLoadingLiveData

    private val _currentPageLiveData = MutableLiveData<Int>()
    val currentPageLiveData: LiveData<Int>
        get() = _currentPageLiveData

    lateinit var popularPagedListLiveData: LiveData<PagedList<Movie>>

    init {
        requestPopular()
    }

    fun refreshPopular(){
        _isRefreshingLiveData.value = true
        requestPopular()
    }

    private fun requestPopular(){
        val factory = MoviePageDataSource.DataSourceFactory(viewModelScope)
        popularPagedListLiveData = MoviePagedListProvider(factory).provide()
        _isRefreshingLiveData.value = false
    }
}