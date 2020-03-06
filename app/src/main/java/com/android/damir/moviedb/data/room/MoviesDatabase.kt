package com.android.damir.moviedb.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.damir.moviedb.domain.entity.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase: RoomDatabase(){

    companion object {
        private const val MOVIES_DATABASE_NAME = "movies"
        private lateinit var instance: MoviesDatabase

        fun get(): MoviesDatabase = instance

        fun initialize(context: Context){
            instance = create(context)
        }

        private fun create(context: Context): MoviesDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MoviesDatabase::class.java,
                MOVIES_DATABASE_NAME
            ).build()
        }
    }

    abstract fun movieDao(): RoomMovieDao
}