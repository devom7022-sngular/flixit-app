package com.sngular.flixitApp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sngular.flixitApp.domain.model.bo.LocationBo
import com.sngular.flixitApp.domain.usecase.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {
    val locations = MutableLiveData<List<LocationBo>>()

    fun onCreate() {
        viewModelScope.launch {
            getLocationUseCase.getLocations(callback = {
                Log.i("Mi respusta", it.toString())
                locations.postValue(it)
            })
        }
    }

    fun setLocation(latitude: String, longitude: String, currentTime: String) {
        getLocationUseCase.setLocation(
            LocationBo(
                lat = latitude,
                long = longitude,
                register = currentTime
            )
        )
    }
}

