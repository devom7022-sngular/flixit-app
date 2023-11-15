package com.sngular.flixitApp.domain.usecase

import com.sngular.flixitApp.data.repository.MovieRepository
import com.sngular.flixitApp.domain.model.bo.MovieBo

import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend fun getAllPopularMovies(): List<MovieBo> {
        return repository.getAllPopularMovies()
    }

    suspend fun getAllRateMovies(): List<MovieBo> {
        return repository.getAllRateMovies()
    }

    suspend fun getAllUpcomingMovies(): List<MovieBo> {
        return repository.getAllUpcomingMovies()
    }
}