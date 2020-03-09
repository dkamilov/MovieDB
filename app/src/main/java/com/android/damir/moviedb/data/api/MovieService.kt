package com.android.damir.moviedb.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService{

    @GET("movie/{type}")
    suspend fun getMovies(
        @Path("type") type: String,
        @Query("page") page: Int
    ): ApiResponse

    @GET("movie/{id}")
    suspend fun getDetails(@Path("id") id: Long): ApiMovie

    @GET("genre/movie/list")
    suspend fun getCategories(): ApiCategory

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getMoviesByCategory(
        @Query("with_genres") id: Long,
        @Query("page") page: Int
    ): ApiResponse
}