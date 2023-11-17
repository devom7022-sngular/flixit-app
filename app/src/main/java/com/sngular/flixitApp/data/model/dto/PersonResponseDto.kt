package com.sngular.flixitApp.data.model.dto

import com.google.gson.annotations.SerializedName
import com.sngular.flixitApp.data.model.dto.MovieDto
import com.sngular.flixitApp.data.model.dto.PersonDto


class PersonResponseDto(
    @SerializedName("page") var page: Int = 0,
    @SerializedName("results") var results: List<PersonDto>? = null,
    @SerializedName("total_pages") var totalPages: Int = 0,
    @SerializedName("total_results") var totalResults: Int = 0
)