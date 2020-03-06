package com.android.damir.moviedb.paging

import androidx.paging.PageKeyedDataSource
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.domain.repository.MovieRepository
import com.android.damir.moviedb.domain.repository.MovieRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviePageDataSource(
    private val type: String,
    private val coroutineScope: CoroutineScope,
    private val movieRepository: MovieRepository = MovieRepositoryImpl()
) : PageKeyedDataSource<Int, Movie>(){


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        coroutineScope.launch {
            val movies = withContext(Dispatchers.IO){
                    movieRepository.getMovies(type, 1)
                }
            callback.onResult(movies, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        coroutineScope.launch {
            val movies = withContext(Dispatchers.IO){
                movieRepository.getMovies(type, params.key)
            }
            callback.onResult(movies, params.key.inc())
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        coroutineScope.launch {
            val movies = withContext(Dispatchers.IO){
                movieRepository.getMovies(type, params.key)
            }
            callback.onResult(movies, params.key.dec())
        }
    }
}