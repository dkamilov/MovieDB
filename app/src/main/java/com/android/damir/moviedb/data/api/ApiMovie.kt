package com.android.damir.moviedb.data.api

import com.google.gson.annotations.SerializedName

data class ApiMovie(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: String,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String?
)