package com.android.damir.thousandmovie.movielist.pagination

import androidx.paging.DataSource
import com.android.damir.thousandmovie.domain.entity.Movie
import kotlinx.coroutines.CoroutineScope

class DataSourceFactory(private val coroutineScope: CoroutineScope): DataSource.Factory<Int, Movie>(){
    override fun create(): DataSource<Int, Movie> {
        return MoviePageDataSource(coroutineScope)
    }

}