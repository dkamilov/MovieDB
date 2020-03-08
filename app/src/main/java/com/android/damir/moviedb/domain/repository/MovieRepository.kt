package com.android.damir.moviedb.domain.repository

import com.android.damir.moviedb.data.api.Category
import com.android.damir.moviedb.domain.entity.Movie

interface MovieRepository{

    suspend fun getMovies(type: String, page: Int): List<Movie>

    suspend fun getCategories(): List<Category>?

    suspend fun getMoviesByCategory(id: Long) : List<Movie>

    suspend fun getDetails(id: Long): Movie?
}