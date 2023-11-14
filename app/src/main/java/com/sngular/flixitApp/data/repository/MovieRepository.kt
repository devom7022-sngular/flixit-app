package com.sngular.flixitApp.data.repository

import com.sngular.flixitApp.data.model.entity.toDatabase
import com.sngular.flixitApp.data.repository.local.MovieLocalRepository
import com.sngular.flixitApp.data.repository.remote.MovieRemoteRepository
import com.sngular.flixitApp.domain.model.bo.MovieBo
import com.sngular.flixitApp.domain.model.bo.toBo
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remote: MovieRemoteRepository,
    private val local: MovieLocalRepository

) {
    private suspend fun getAllPopularRemoteMovies(): List<MovieBo> =
        remote.getPopularMovies().results!!.map { it.toBo() }

    private suspend fun getAllPopularLocalMovies(): List<MovieBo> =
        local.getPopularMovies().map { it.toBo() }

    suspend fun getAllPopularMovies(): List<MovieBo> {
        var movies = getAllPopularLocalMovies()
        if (movies.isEmpty()) {
            local.clearMovies()
            movies = getAllPopularRemoteMovies()
            local.insertMovies(movies.map { it.toDatabase() })
        }
        return movies
    }
}