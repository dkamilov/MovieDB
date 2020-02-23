package com.android.damir.thousandmovie.movielist.pagination

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.android.damir.thousandmovie.domain.entity.Movie

class MoviePagedListProvider(private val factory: DataSource.Factory<Int, Movie>){

    fun provide(): LiveData<PagedList<Movie>>{

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(true)
            .build()

        return LivePagedListBuilder(factory, config)
            .build()
    }
}