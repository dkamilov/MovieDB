package com.android.damir.moviedb.domain.repository

import com.android.damir.moviedb.data.api.ApiFactory
import com.android.damir.moviedb.data.api.Category
import com.android.damir.moviedb.data.api.MovieService
import com.android.damir.moviedb.data.room.MoviesDatabase
import com.android.damir.moviedb.data.room.RoomMovieDao
import com.android.damir.moviedb.domain.ApiMovieMapper
import com.android.damir.moviedb.domain.entity.Movie
import timber.log.Timber

class MovieRepositoryImpl(
    private val movieService: MovieService = ApiFactory.apiService,
    private val moviesDatabase: MoviesDatabase = MoviesDatabase.get(),
    private val apiMovieMapper: ApiMovieMapper = ApiMovieMapper()
) : MovieRepository {

    private val movieDao: RoomMovieDao = moviesDatabase.movieDao()

    override suspend fun getMovies(type: String, page: Int): List<Movie> {
        val response = try {
            movieService.getMovies(type, page)
        } catch (e: Exception) {
            Timber.e(e)
            return getLocalMovies(type)
        }
        val movies = response.results.map {
            val movie = apiMovieMapper.map(it, type)
            movieDao.insertAll(movie)
            movie
        }
        return movies
    }

    override suspend fun getCategories(): List<Category>? {
        val response = try {
            movieService.getCategories()
        }catch (e: Exception){
            Timber.e(e)
            return null
        }
        return response.categories
    }

    override suspend fun getDetails(id: Long): Movie? {
        return movieDao.getDetails(id) ?: return requestMovie(id)
    }

    private suspend fun getLocalMovies(type: String): List<Movie> {
        return movieDao.getMovies(type)
    }

    private suspend fun requestMovie(id: Long): Movie? {
        val response = try {
            movieService.getDetails(id)
        } catch (e: Exception){
            Timber.e(e)
            return null
        }
        return apiMovieMapper.map(response)
    }
}
