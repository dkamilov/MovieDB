package com.android.damir.moviedb.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.android.damir.moviedb.domain.entity.Movie
import kotlinx.coroutines.CoroutineScope

class DataSourceFactory(private val type: String, private val coroutineScope: CoroutineScope): DataSource.Factory<Int, Movie>(){
    val moviesLiveData = MutableLiveData<MoviePageDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val moviePageDataSource = MoviePageDataSource(type, coroutineScope)
        moviesLiveData.postValue(moviePageDataSource)
        return moviePageDataSource
    }

}