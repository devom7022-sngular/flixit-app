package com.sngular.flixitApp.domain.model.bo

data class LocationBo(
    val id: Int?,
    val long: Double,
    val lat: Double,
    val register: String,
)

/*fun LocationDto.toBo() = LocationBo(
    id,
    long,
    lat,
    register,
)

fun LocationEntity.toBo() = LocationBo(
    id,
    long,
    lat,
    register,
)*/