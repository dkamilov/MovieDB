package com.android.damir.thousandmovie.data.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tmdbApiKey: String) : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain
            .request()
            .url()
            .newBuilder()
            .addQueryParameter("api_key", tmdbApiKey)
            .build()

        val newRequest = chain
            .request()
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}