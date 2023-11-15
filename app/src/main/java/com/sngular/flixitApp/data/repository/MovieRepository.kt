package com.sngular.flixitApp.data.repository

import com.sngular.flixitApp.data.repository.local.MovieLocalRepository
import com.sngular.flixitApp.data.repository.remote.MovieRemoteRepository
import com.sngular.flixitApp.domain.model.bo.MovieBo
import com.sngular.flixitApp.util.MOVIE_TYPE
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remote: MovieRemoteRepository,
    private val local: MovieLocalRepository
) {
    private suspend fun getAllRemoteMovies(enum: Enum<MOVIE_TYPE>): List<MovieBo> {
        return when (enum) {
            MOVIE_TYPE.POPULAR -> remote.getAllPopularMoviesRemote().results!!.map { it.toBo() }
            MOVIE_TYPE.RATED -> remote.getAllRateMoviesRemote().results!!.map { it.toBo() }
            else -> remote.getAllUpcomingMoviesRemote().results!!.map { it.toBo() }
        }
    }

    private suspend fun getAllLocalMovies(enum: Enum<MOVIE_TYPE>): List<MovieBo> {
        return when (enum) {
            MOVIE_TYPE.POPULAR -> local.getAllPopularMoviesLocal().map { it.toBo() }
            MOVIE_TYPE.RATED -> local.getAllRateMoviesLocal().map { it.toBo() }
            else -> local.getAllUpcomingMoviesLocal().map { it.toBo() }
        }
    }


    suspend fun getAllPopularMovies(): List<MovieBo> {
        var movies = getAllLocalMovies(MOVIE_TYPE.POPULAR)
        if (movies.isEmpty()) {
            local.clearAllPopularMovies()
            movies = getAllRemoteMovies(MOVIE_TYPE.POPULAR)
            local.insertAllPopularMovies(movies.map { it.toPopularEntity() })
        }
        return movies
    }

    suspend fun getAllRateMovies(): List<MovieBo> {
        var movies = getAllLocalMovies(MOVIE_TYPE.RATED)
        if (movies.isEmpty()) {
            local.clearAllRateMovies()
            movies = getAllRemoteMovies(MOVIE_TYPE.RATED)
            local.insertAllRateMovies(movies.map { it.toRateEntity() })
        }
        return movies
    }

    suspend fun getAllUpcomingMovies(): List<MovieBo> {
        var movies = getAllLocalMovies(MOVIE_TYPE.UPCOMING)
        if (movies.isEmpty()) {
            local.clearAllUpcomingMovies()
            movies = getAllRemoteMovies(MOVIE_TYPE.UPCOMING)
            local.insertAllUpcomingMovies(movies.map { it.toUpcomingEntity() })
        }
        return movies
    }

}