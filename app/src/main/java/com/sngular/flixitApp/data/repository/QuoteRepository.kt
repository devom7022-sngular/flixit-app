package com.sngular.flixitApp.data.repository

import com.sngular.flixitApp.data.model.dao.MovieDao
import com.sngular.flixitApp.data.model.entity.MovieEntity
import com.sngular.flixitApp.data.repository.remote.MovieRemoteRepository
import com.sngular.flixitApp.domain.model.bo.MovieBo
import com.sngular.flixitApp.domain.model.bo.toDomain
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remote: MovieRemoteRepository,
    private val movieDao: MovieDao
) {
    suspend fun getAllPopularMovies(): List<MovieBo> =
        remote.getPopularMovies().results!!.map { it.toDomain() }

    //    suspend fun getAllMoviesFromDB(): List<Movie> = MovieDao.getAllMovies().map { it.toDomain() }
    suspend fun insertMovies(movies: List<MovieEntity>) {
        movieDao.insertAll(movies)
    }

    suspend fun clearMovies() {
        movieDao.deleteAllMovies()

    }
}