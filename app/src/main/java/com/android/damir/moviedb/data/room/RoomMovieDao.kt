package com.android.damir.moviedb.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.damir.moviedb.domain.entity.Movie

@Dao
interface RoomMovieDao {

    @Query("SELECT * FROM movie WHERE type = :type ")
    suspend fun getMovies(type: String): List<Movie>

    @Query("SELECT * FROM movie WHERE id = :id LIMIT 1")
    suspend fun getDetails(id: Long): Movie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg movie: Movie)
}