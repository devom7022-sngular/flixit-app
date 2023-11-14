package com.sngular.flixitApp.domain.usecase

import com.sngular.flixitApp.data.repository.MovieRepository
import com.sngular.flixitApp.domain.model.bo.MovieBo

import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(): List<MovieBo> {
        return repository.getAllPopularMovies()
    }
}