package com.sngular.flixitApp.data.repository

import android.util.Log
import com.sngular.flixitApp.data.repository.local.LocationLocalRepository
import com.sngular.flixitApp.data.repository.remote.LocationRemoteRepository
import com.sngular.flixitApp.domain.model.bo.LocationBo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val remote: LocationRemoteRepository,
    private val local: LocationLocalRepository

) {
    internal suspend fun getAllRemoteLocations(callback: (MutableList<LocationBo>) -> Unit) {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            remote.getLocations { it ->
                Log.i("Location callback", it.toString())
                callback(it)
            }
        }
    }

    fun setLocationService(locationBo: LocationBo) {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            remote.setLocationRemote(locationBo)
        }
    }
}