package com.android.damir.thousandmovie.data.mapper

import com.android.damir.thousandmovie.data.api.ApiMovie
import com.android.damir.thousandmovie.domain.entity.Movie

class ApiMovieMapper : Mapper<ApiMovie, Movie> {
    override fun map(value: ApiMovie) = Movie(
        id = value.id,
        title = value.title,
        voteAverage = value.voteAverage,
        backdropPath = value.backdropPath,
        overview = value.overview,
        releaseDate = value.releaseDate
    )
}