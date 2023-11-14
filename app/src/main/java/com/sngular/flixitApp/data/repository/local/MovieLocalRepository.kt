package com.sngular.flixitApp.data.repository.local

import com.sngular.flixitApp.data.model.dao.MovieDao
import com.sngular.flixitApp.data.model.entity.MovieEntity
import javax.inject.Inject

class MovieLocalRepository @Inject constructor(private val movieDao: MovieDao) {

    suspend fun getPopularMovies(): List<MovieEntity> = movieDao.getAllMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) {
        movieDao.insertAll(movies)
    }

    suspend fun clearMovies() {
        movieDao.deleteAllMovies()

    }
}