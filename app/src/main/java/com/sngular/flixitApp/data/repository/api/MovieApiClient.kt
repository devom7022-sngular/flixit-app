package com.sngular.flixitApp.data.repository.api

import com.sngular.flixitApp.data.model.remote.dto.MoviesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiClient {
    @GET("popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<MoviesResponseDto>
}