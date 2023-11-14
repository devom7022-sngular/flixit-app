package com.sngular.flixitApp.data.model.dto

import com.google.gson.annotations.SerializedName

class LocationDto(
    @SerializedName("long") var long: String? = "",
    @SerializedName("lat") var lat: String? = "",
    @SerializedName("register") var register: String? = "",
) {
}