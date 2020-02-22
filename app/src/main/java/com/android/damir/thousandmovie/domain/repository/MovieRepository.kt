package com.android.damir.thousandmovie.domain.repository

import com.android.damir.thousandmovie.domain.entity.Movie

interface MovieRepository{

    suspend fun getPopular(): List<Movie>

    suspend fun getDetails(id: Long): Movie?
}