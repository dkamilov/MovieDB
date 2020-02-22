package com.android.damir.thousandmovie.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomMovieDao {

    @Query("SELECT * FROM movies")
    suspend fun getPopular(): List<RoomMovie>

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getDetails(id: Long): RoomMovie?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg movie: RoomMovie)
}