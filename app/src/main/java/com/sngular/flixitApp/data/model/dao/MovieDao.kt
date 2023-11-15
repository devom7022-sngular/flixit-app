package com.sngular.flixitApp.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sngular.flixitApp.data.model.entity.PopularMovieEntity
import com.sngular.flixitApp.data.model.entity.RateMovieEntity
import com.sngular.flixitApp.data.model.entity.UpcomingMovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM popular_table ORDER BY title DESC")
    suspend fun getAllPopularMovies(): List<PopularMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPopularMovies(movies: List<PopularMovieEntity>)

    @Query("DELETE FROM popular_table")
    suspend fun deleteAllPopularMovies()

    @Query("SELECT * FROM rate_table ORDER BY title DESC")
    suspend fun getAllRateMovies(): List<RateMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRateMovies(movies: List<RateMovieEntity>)

    @Query("DELETE FROM rate_table")
    suspend fun deleteAllRateMovies()

    @Query("SELECT * FROM upcoming_table ORDER BY title DESC")
    suspend fun getAllUpcomingMovies(): List<UpcomingMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUpcomingMovies(movies: List<UpcomingMovieEntity>)

    @Query("DELETE FROM upcoming_table")
    suspend fun deleteAllUpcomingMovies()

}