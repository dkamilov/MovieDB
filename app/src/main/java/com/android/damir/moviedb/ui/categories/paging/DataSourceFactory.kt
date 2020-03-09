package com.android.damir.moviedb.ui.categories.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.android.damir.moviedb.domain.entity.Movie
import kotlinx.coroutines.CoroutineScope

class DataSourceFactory (private val id: Long, private val coroutineScope: CoroutineScope): DataSource.Factory<Int, Movie>() {
    val moviesLiveData = MutableLiveData<CategoryPageDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val categoryPageDataSource = CategoryPageDataSource(id, coroutineScope)
        moviesLiveData.postValue(categoryPageDataSource)
        return categoryPageDataSource
    }

}