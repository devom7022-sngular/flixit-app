package com.sngular.flixitApp.data.model.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sngular.flixitApp.domain.model.bo.LocationBo
import com.sngular.flixitApp.domain.model.bo.MovieBo
import kotlinx.parcelize.Parcelize

@Parcelize
class LocationDto(
    @SerializedName("long") var long: String? = "",
    @SerializedName("lat") var lat: String? = "",
    @SerializedName("register") var register: String? = "",
) : Parcelable, BaseDto<LocationBo> {
    override fun toBo(): LocationBo {
        return LocationBo(
            long = long!!, lat = lat!!, register = register!!
        )
    }
}