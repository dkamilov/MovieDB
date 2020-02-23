package com.android.damir.thousandmovie.data.room

import androidx.room.Entity


@Entity(
    tableName = "movies",
    primaryKeys = ["id"]
)
data class RoomMovie(
    val id: String,
    val title: String,
    val voteAverage: String,
    val backdropPath: String?,
    val overview: String,
    val releaseDate: String?
)