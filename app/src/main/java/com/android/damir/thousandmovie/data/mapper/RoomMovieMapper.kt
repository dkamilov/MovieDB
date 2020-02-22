package com.android.damir.thousandmovie.data.mapper

import com.android.damir.thousandmovie.data.room.RoomMovie
import com.android.damir.thousandmovie.domain.entity.Movie

class MovieToRoomMapper : Mapper<Movie, RoomMovie> {

    override fun map(value: Movie) = RoomMovie(
        id = value.id,
        title = value.title,
        voteAverage = value.voteAverage,
        backdropPath = value.backdropPath,
        overview = value.overview,
        releaseDate = value.releaseDate
    )
}

class RoomToMovieMapper : Mapper<RoomMovie, Movie> {

    override fun map(value: RoomMovie) = Movie(
        id = value.id,
        title = value.title,
        voteAverage = value.voteAverage,
        backdropPath = value.backdropPath,
        overview = value.overview,
        releaseDate = value.releaseDate
    )

}