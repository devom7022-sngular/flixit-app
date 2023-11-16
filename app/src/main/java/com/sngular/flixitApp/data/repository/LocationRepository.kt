package com.sngular.flixitApp.data.repository

import com.sngular.flixitApp.data.repository.local.LocationLocalRepository
import com.sngular.flixitApp.data.repository.remote.LocationRemoteRepository
import com.sngular.flixitApp.domain.model.bo.LocationBo
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val remote: LocationRemoteRepository,
    private val local: LocationLocalRepository

) {
    private suspend fun getAllRemoteLocations(): List<LocationBo> =
        remote.getLocations().map { it.toBo() }

    private suspend fun getAllLocalLocations(): List<LocationBo> =
        local.getLocations().map { it.toBo() }

    suspend fun getAllLocations(): List<LocationBo> {
        var locations = getAllRemoteLocations()
        if (locations.isEmpty()) {
            local.clearLocations()
            locations = getAllRemoteLocations()
            local.insertLocations(locations.map { it.toEntity() })
        }
        return locations
    }
}