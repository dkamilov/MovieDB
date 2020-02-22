package com.android.damir.thousandmovie.moviedetails

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

class MovieDetailsViewModel(
    private val movieId: Long,
    private val movieRepository: MovieRepository = MovieRepositoryImpl()
) : ViewModel(){

    private val movieDetailLiveData = MutableLiveData<Movie>()

    fun getDetails(): LiveData<Movie> {
        if(movieDetailLiveData.value == null){
            requestDetails(movieId)
        }
        return movieDetailLiveData
    }

    private fun requestDetails(id: Long){
        viewModelScope.launch {
            val details = withContext(Dispatchers.IO){
                movieRepository.getDetails(id)
            }
            movieDetailLiveData.value = details
        }
    }
}