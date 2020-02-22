package com.android.damir.thousandmovie.data.api

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName ("results") val results: List<ApiMovie>
)