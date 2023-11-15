package com.sngular.flixitApp.domain.usecase

import com.sngular.flixitApp.data.repository.LocationRepository
import com.sngular.flixitApp.data.repository.MovieRepository
import com.sngular.flixitApp.domain.model.bo.LocationBo
import com.sngular.flixitApp.domain.model.bo.MovieBo

import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val repository: LocationRepository) {
    suspend operator fun invoke(): List<LocationBo> {
        return repository.getAllLocations()
    }
}