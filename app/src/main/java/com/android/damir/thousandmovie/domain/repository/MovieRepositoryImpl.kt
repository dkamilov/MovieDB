package com.android.damir.thousandmovie.domain.repository

import com.android.damir.thousandmovie.data.api.ApiFactory
import com.android.damir.thousandmovie.data.api.MovieService
import com.android.damir.thousandmovie.data.mapper.ApiMovieMapper
import com.android.damir.thousandmovie.data.mapper.MovieToRoomMapper
import com.android.damir.thousandmovie.data.mapper.RoomToMovieMapper
import com.android.damir.thousandmovie.data.room.MoviesDatabase
import com.android.damir.thousandmovie.data.room.RoomMovieDao
import com.android.damir.thousandmovie.domain.entity.Movie
import timber.log.Timber

class MovieRepositoryImpl(
    private val movieService: MovieService = ApiFactory.apiService,
    private val moviesDatabase: MoviesDatabase = MoviesDatabase.get(),
    private val apiMovieMapper: ApiMovieMapper = ApiMovieMapper(),
    private val roomToMovieMapper: RoomToMovieMapper = RoomToMovieMapper(),
    private val movieToRoomMapper: MovieToRoomMapper = MovieToRoomMapper()
) : MovieRepository {

    private val movieDao: RoomMovieDao = moviesDatabase.movieDao()

    override suspend fun getPopular(): List<Movie> {
        val response = try {
            movieService.getPopular()
        } catch (e: Exception){
            Timber.e(e, "message%s", e.message)

            return getLocalMovies()
        }

        val moviesList = response.results.map {
            val movie = apiMovieMapper.map(it)
            val roomMovie = movieToRoomMapper.map(movie)
            movieDao.insertAll(roomMovie)

            movie
        }

        return moviesList
    }

    override suspend fun getDetails(id: Long): Movie? {
        val details = movieDao.getDetails(id)
        return details?.let{
            roomToMovieMapper.map(it)
        } ?: requestMovie(id)
    }

    private suspend fun getLocalMovies(): List<Movie>{
        val movies = movieDao.getPopular()
        return movies.map {
            roomToMovieMapper.map(it)
        }
    }

    private suspend fun requestMovie(id: Long): Movie? {
        val response = try{
            movieService.getDetails(id)
        }catch (e: Exception){
            Timber.e(e.message, e)
            return null
        }
        return apiMovieMapper.map(response)
    }
}
