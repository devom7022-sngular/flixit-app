package com.sngular.flixitApp.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sngular.flixitApp.data.model.entity.UpcomingMovieEntity

@Dao
interface UpcomingMovieDao {

    @Query("SELECT * FROM upcoming_table ORDER BY title DESC")
    suspend fun getAllUpcomingMovies(): List<UpcomingMovieEntity>

    @Insert(entity = UpcomingMovieEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUpcomingMovies(movies: List<UpcomingMovieEntity>)

    @Query("DELETE FROM upcoming_table")
    suspend fun deleteAllUpcomingMovies()

}