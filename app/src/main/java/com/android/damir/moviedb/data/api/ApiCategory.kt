package com.android.damir.moviedb.data.api

import com.google.gson.annotations.SerializedName

data class ApiCategory(
    @SerializedName("genres") val categories: List<Category>
)

data class Category(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)