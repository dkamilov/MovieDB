package com.android.damir.thousandmovie.data.api

import com.android.damir.thousandmovie.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(AuthInterceptor(BuildConfig.TMDB_API_KEY))
        .build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: MovieService = retrofit().create(MovieService::class.java)
}