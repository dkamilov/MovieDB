package com.android.damir.moviedb.ui.search.paging

import androidx.paging.PageKeyedDataSource
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.domain.repository.MovieRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchPageDataSource(
    private val query: String,
    private val coroutineScope: CoroutineScope,
    private val moviesRepository: MovieRepositoryImpl = MovieRepositoryImpl()
) : PageKeyedDataSource<Int, Movie>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        coroutineScope.launch {
            val movies = withContext(Dispatchers.IO) {
                moviesRepository.searchMovies(query, 1)
            }
            callback.onResult(movies, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        coroutineScope.launch {
            val movies = withContext(Dispatchers.IO) {
                moviesRepository.searchMovies(query, params.key)
            }
            callback.onResult(movies, params.key.inc())
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        coroutineScope.launch {
            val movies = withContext(Dispatchers.IO) {
                moviesRepository.searchMovies(query, params.key)
            }
            callback.onResult(movies, params.key.dec())
        }
    }

}