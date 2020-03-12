package com.android.damir.moviedb.ui.search.paging

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.android.damir.moviedb.domain.entity.Movie

class SearchPagedListProvider(private val factory: DataSourceFactory) {

    fun provide() : LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(true)
            .build()

        return LivePagedListBuilder(factory, config)
            .build()
    }
}