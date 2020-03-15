package com.android.damir.moviedb.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.ui.search.paging.DataSourceFactory
import com.android.damir.moviedb.ui.search.paging.SearchPagedListProvider

class SearchViewModel() : ViewModel() {

    fun searchMovies(query: String): LiveData<PagedList<Movie>> {
        val factory = DataSourceFactory(query, viewModelScope)
        return SearchPagedListProvider(factory).provide()
    }

}