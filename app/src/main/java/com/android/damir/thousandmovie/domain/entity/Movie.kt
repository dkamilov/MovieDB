package com.android.damir.thousandmovie.domain.entity

data class Movie(
    val id: String,
    val title: String,
    val voteAverage: String,
    val backdropPath: String,
    val overview: String,
    val releaseDate: String
)