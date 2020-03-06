package com.android.damir.moviedb.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private val authInterceptor = Interceptor{chain ->
        val newUrl = chain
            .request()
            .url()
            .newBuilder()
            .addQueryParameter("api_key", "44e56ddebe9a0e483337cefbfbd812f5")
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(authInterceptor)
        .build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: MovieService = retrofit().create(MovieService::class.java)
}