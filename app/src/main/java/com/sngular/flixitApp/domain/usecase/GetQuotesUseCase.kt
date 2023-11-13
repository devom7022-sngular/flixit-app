package com.sngular.flixitApp.domain.usecase

import com.sngular.flixitApp.data.repository.MovieRepository
import com.sngular.flixitApp.data.model.entity.toDatabase
import com.sngular.flixitApp.domain.model.bo.MovieBo

import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(): List<MovieBo> {
        val movies = repository.getAllPopularMovies()
        return if (movies.isNotEmpty()) {
            repository.clearMovies()
            repository.insertMovies(movies.map { it.toDatabase() })
            movies
        } else {
            emptyList()
            //repository.getAllMoviesFromDB()
        }


    }

    //suspend operator fun invoke(): List<MovieBo> = repository.getAllMoviesFromApi()
}