package com.android.damir.moviedb.ui.search.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.android.damir.moviedb.domain.entity.Movie
import kotlinx.coroutines.CoroutineScope

class DataSourceFactory (
    private val query: String,
    private val coroutineScope: CoroutineScope
): DataSource.Factory<Int, Movie>() {

    val moviesLiveData = MutableLiveData<SearchPageDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val searchPageDataSource = SearchPageDataSource(query, coroutineScope)
        moviesLiveData.postValue(searchPageDataSource)
        return searchPageDataSource
    }

}