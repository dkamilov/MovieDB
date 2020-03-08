package com.android.damir.moviedb.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.domain.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieByCategoryViewModel(
    private val movieRepository: MovieRepositoryImpl = MovieRepositoryImpl()
) : ViewModel() {

    private val moviesByCategory = MutableLiveData<List<Movie>>()

    fun getMoviesByCategory(id: Long): LiveData<List<Movie>> {
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO){
                movieRepository.getMoviesByCategory(id)
            }
            moviesByCategory.value = movies
        }
        return moviesByCategory
    }

}