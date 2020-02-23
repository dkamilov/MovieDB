package com.android.damir.thousandmovie.movielist.pagination

import androidx.paging.PageKeyedDataSource
import com.android.damir.thousandmovie.domain.entity.Movie
import com.android.damir.thousandmovie.domain.repository.MovieRepository
import com.android.damir.thousandmovie.domain.repository.MovieRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

var isRefreshing: Boolean = true
class MoviePageDataSource(
    private val coroutineScope: CoroutineScope,
    private val movieRepository: MovieRepository = MovieRepositoryImpl()
) : PageKeyedDataSource<Int, Movie>(){


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        coroutineScope.launch {
            val movies = withContext(Dispatchers.IO){
                    movieRepository.getPopular(1)
                }
            callback.onResult(movies, null, 2)
            isRefreshing = false
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        coroutineScope.launch {
            val movies = withContext(Dispatchers.IO){
                movieRepository.getPopular(params.key)
            }
            callback.onResult(movies, params.key.inc())
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        coroutineScope.launch {
            val movies = withContext(Dispatchers.IO){
                movieRepository.getPopular(params.key)
            }
            callback.onResult(movies, params.key.dec())
        }
    }
}