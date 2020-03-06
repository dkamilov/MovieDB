package com.android.damir.moviedb.domain.entity

import androidx.room.Entity

@Entity(
    tableName = "movie",
    primaryKeys = ["id"]
)
data class Movie(
    val id: String,
    val title: String,
    val voteAverage: String,
    val backdropPath: String?,
    val overview: String,
    val releaseDate: String?,
    val type: String? = null
)