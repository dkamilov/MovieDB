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

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean>
        get() = _isLoadingLiveData

    private val _currentPageLiveData = MutableLiveData<Int>()
    val currentPageLiveData: LiveData<Int>
        get() = _currentPageLiveData

    init {
        requestPopular()
    }

    fun refreshPopular(){
        _isRefreshingLiveData.value = true
        requestPopular()
    }

    fun requestPopular(page: Int = 1) {
        Timber.i("request popular, page = $page")
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO){
                movieRepository.getPopular(page)
            }
            _popularListLiveData.value = movies
            _isRefreshingLiveData.value = false
            _isLoadingLiveData.value = false
            _currentPageLiveData.value = page
        }
    }
}