package com.sngular.flixitApp.data.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sngular.flixitApp.data.model.dao.LocationDao
import com.sngular.flixitApp.data.model.dao.MovieDao
import com.sngular.flixitApp.data.model.entity.LocationEntity
import com.sngular.flixitApp.data.model.entity.PopularMovieEntity
import com.sngular.flixitApp.data.model.entity.RateMovieEntity
import com.sngular.flixitApp.data.model.entity.UpcomingMovieEntity

@Database(
    entities = [
        PopularMovieEntity::class,
        RateMovieEntity::class,
        UpcomingMovieEntity::class,
        LocationEntity::class],
    version = 1
)
abstract class FlixitAppDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao

    abstract fun getLocationDao(): LocationDao
}