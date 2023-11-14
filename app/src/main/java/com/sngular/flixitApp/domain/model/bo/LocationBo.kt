package com.sngular.flixitApp.domain.model.bo

import com.sngular.flixitApp.data.model.dto.LocationDto
import com.sngular.flixitApp.data.model.entity.LocationEntity

data class LocationBo(
    val long: String,
    val lat: String,
    val register: String,
)

fun LocationDto.toBo() = LocationBo(
    long!!,
    lat!!,
    register!!,
)

fun LocationEntity.toBo() = LocationBo(
    long!!,
    lat.toString(),
    register!!,
)