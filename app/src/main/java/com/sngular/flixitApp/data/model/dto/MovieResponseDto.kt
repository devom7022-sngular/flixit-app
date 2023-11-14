package com.sngular.flixitApp.data.model.remote.dto

import com.google.gson.annotations.SerializedName
import com.sngular.flixitApp.data.model.dto.MovieDto


class MovieResponseDto(
    @SerializedName("page") var page: Int = 0,
    @SerializedName("results") var results: List<MovieDto>? = null,
    @SerializedName("total_pages") var totalPages: Int = 0,
    @SerializedName("total_results") var totalResults: Int = 0
)