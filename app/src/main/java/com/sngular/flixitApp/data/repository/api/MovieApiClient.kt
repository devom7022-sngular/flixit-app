package com.sngular.flixitApp.data.repository.api

import com.sngular.flixitApp.data.model.remote.dto.MovieResponseDto
import com.sngular.flixitApp.data.model.dto.PersonResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiClient {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieResponseDto>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieResponseDto>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieResponseDto>

    @GET("person/popular")
    suspend fun getPersonPopular(
        @Query("api_key") apiKey: String
    ): Response<PersonResponseDto>

}