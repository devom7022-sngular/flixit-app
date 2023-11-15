package com.sngular.flixitApp.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sngular.flixitApp.data.model.entity.PopularMovieEntity
import com.sngular.flixitApp.data.model.entity.RateMovieEntity
import com.sngular.flixitApp.data.model.entity.UpcomingMovieEntity

@Dao
interface RateMovieDao {

    @Query("SELECT * FROM rate_table ORDER BY title DESC")
    suspend fun getAllRateMovies(): List<RateMovieEntity>

    @Insert(entity = RateMovieEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRateMovies(movies: List<RateMovieEntity>)

    @Query("DELETE FROM rate_table")
    suspend fun deleteAllRateMovies()

}