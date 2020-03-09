package com.android.damir.moviedb.ui.categories.paging

import androidx.paging.PageKeyedDataSource
import com.android.damir.moviedb.data.api.Category
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.domain.repository.MovieRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryPageDataSource(
    private val id: Long,
    private val coroutineScope: CoroutineScope,
    private val movieRepository: MovieRepositoryImpl = MovieRepositoryImpl()
) : PageKeyedDataSource<Int, Movie>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        coroutineScope.launch {
            val movies = withContext(Dispatchers.IO){
                movieRepository.getMoviesByCategory(id, 1)
            }
            callback.onResult(movies, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        coroutineScope.launch {
            val movies = withContext(Dispatchers.IO){
                movieRepository.getMoviesByCategory(id, params.key)
            }
            callback.onResult(movies, params.key.inc())
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        coroutineScope.launch {
            val movies = withContext(Dispatchers.IO){
                movieRepository.getMoviesByCategory(id, params.key)
            }
            callback.onResult(movies, params.key.dec())
        }
    }

}