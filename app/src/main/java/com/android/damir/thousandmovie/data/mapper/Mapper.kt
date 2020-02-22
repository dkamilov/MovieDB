package com.android.damir.thousandmovie.data.mapper

interface Mapper<V, T>{
    fun map(value: V): T
}