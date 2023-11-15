package com.sngular.flixitApp.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sngular.flixitApp.data.model.entity.PopularMovieEntity
import com.sngular.flixitApp.data.model.entity.RateMovieEntity
import com.sngular.flixitApp.data.model.entity.UpcomingMovieEntity

@Dao
interface PopularMovieDao {

    @Query("SELECT * FROM popular_table ORDER BY title DESC")
    suspend fun getAllPopularMovies(): List<PopularMovieEntity>

    @Insert(entity = PopularMovieEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPopularMovies(movies: List<PopularMovieEntity>)

    @Query("DELETE FROM popular_table")
    suspend fun deleteAllPopularMovies()
}