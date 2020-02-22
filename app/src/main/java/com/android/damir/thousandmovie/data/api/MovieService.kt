package com.android.damir.thousandmovie.data.api

import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService{

    @GET("movie/popular?api_key=44e56ddebe9a0e483337cefbfbd812f5")
    suspend fun getPopular(): ApiResponse

    @GET("movie/{id}?api_key=44e56ddebe9a0e483337cefbfbd812f5")
    suspend fun getDetails(@Path("id") id: Long): ApiMovie
}