package com.sngular.flixitApp.domain.usecase

import com.sngular.flixitApp.data.repository.LocationRepository
import com.sngular.flixitApp.domain.model.bo.LocationBo
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val repository: LocationRepository) {
    suspend fun getLocations(callback: (MutableList<LocationBo>) -> Unit) {
        return repository.getAllRemoteLocations(callback = {
            callback(it)
        })
    }

    fun setLocation(locationBo: LocationBo) {
        return repository.setLocationService(locationBo)
    }
}