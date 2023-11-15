package com.sngular.flixitApp.data.repository.local

import com.sngular.flixitApp.data.model.dao.LocationDao
import com.sngular.flixitApp.data.model.entity.LocationEntity
import javax.inject.Inject

class LocationLocalRepository @Inject constructor(private val locationDao: LocationDao) {

    suspend fun getLocations(): List<LocationEntity> = locationDao.getAllLocations()

    suspend fun insertLocations(location: List<LocationEntity>) {
        locationDao.insertAllLocations(location)
    }

    suspend fun clearLocations() {
        locationDao.deleteAllLocations()

    }
}