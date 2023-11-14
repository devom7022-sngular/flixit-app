package com.sngular.flixitApp.data.repository.remote

import com.sngular.flixitApp.data.model.remote.dto.MovieResponseDto
import com.sngular.flixitApp.data.repository.api.MovieApiClient
import com.sngular.flixitApp.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteRepository @Inject constructor(private val api: MovieApiClient){

    suspend fun getPopularMovies(): MovieResponseDto {
        return withContext(Dispatchers.IO) {
            val response: Response<MovieResponseDto> = api.getPopularMovies(Constants.API_KEY)
            response.body()!!
        }
    }
}