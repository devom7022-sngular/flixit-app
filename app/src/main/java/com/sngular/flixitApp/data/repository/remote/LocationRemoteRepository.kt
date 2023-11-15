package com.sngular.flixitApp.data.repository.remote

import com.sngular.flixitApp.data.model.dto.LocationDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationRemoteRepository @Inject constructor() {

    suspend fun getLocations(): List<LocationDto> {
        return withContext(Dispatchers.IO) {
            val response: List<LocationDto> =
                data()//api.getPopularMovies(Constants.API_KEY)
            response
        }
    }

    fun data(): List<LocationDto> {
        return arrayOf(
            LocationDto("10.0", "3.5", "2023 - 12 - 10"),
            LocationDto("29.0", "3", "2023 - 12 - 11"),
            LocationDto("19.0", "5", "2023 - 12 - 12"),
            LocationDto("90.0", "3.5", "2023 - 12 - 13"),
            LocationDto("19.0", "3.5", "2023 - 12 - 14")
        ).toList()
    }
}