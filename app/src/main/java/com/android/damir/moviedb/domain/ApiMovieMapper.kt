package com.android.damir.moviedb.domain

import com.android.damir.moviedb.data.api.ApiMovie
import com.android.damir.moviedb.domain.entity.Movie

class ApiMovieMapper {
    fun map(apiMovie: ApiMovie, type: String? = null) =
        Movie(
            id = apiMovie.id,
            backdropPath = apiMovie.backdropPath,
            overview = apiMovie.overview,
            releaseDate = apiMovie.releaseDate,
            title = apiMovie.title,
            voteAverage = apiMovie.voteAverage,
            type = type
        )
}