package com.android.damir.thousandmovie.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.damir.thousandmovie.domain.entity.Movie
import com.android.damir.thousandmovie.domain.repository.MovieRepository
import com.android.damir.thousandmovie.domain.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MovieListViewModel(
    private val movieRepository: MovieRepository = MovieRepositoryImpl()
) : ViewModel(){

    private val _popularListLiveData = MutableLiveData<List<Movie>>()
    val popularListLiveData: LiveData<List<Movie>>
        get() = _popularListLiveData

    private val _isRefreshingLiveData = MutableLiveData<Boolean>()
    val isRefreshingLiveData: LiveData<Boolean>
        get() = _isRefreshingLiveData

    init {
        Timber.i("init")
        requestPopular()
    }

    fun refreshPopular(){
        Timber.i("refreshPopular")
        _isRefreshingLiveData.value = true
        Timber.i("isRefreshing ${_isRefreshingLiveData.value}")
        requestPopular()
    }

    private fun requestPopular() {
        Timber.i("requestPopular")
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO){
                movieRepository.getPopular()
            }
            _popularListLiveData.value = movies
            _isRefreshingLiveData.value = false
        }
    }
}