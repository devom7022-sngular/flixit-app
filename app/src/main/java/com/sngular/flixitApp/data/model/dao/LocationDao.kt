package com.sngular.flixitApp.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sngular.flixitApp.data.model.entity.LocationEntity

@Dao
interface LocationDao {

    @Query("SELECT * FROM location_table")
    suspend fun getAllLocations(): List<LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLocations(movies: List<LocationEntity>)

    @Query("DELETE FROM location_table")
    suspend fun deleteAllLocations()
}