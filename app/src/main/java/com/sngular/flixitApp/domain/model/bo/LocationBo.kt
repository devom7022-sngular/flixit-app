package com.sngular.flixitApp.domain.model.bo

import android.os.Parcelable
import com.sngular.flixitApp.data.model.entity.LocationEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationBo(
    val long: String,
    val lat: String,
    val register: String,
) : Parcelable, BaseBo<LocationEntity> {
    override fun toEntity(): LocationEntity {
        return LocationEntity(
            long = long,
            lat = lat,
            register = register,
        )
    }

}