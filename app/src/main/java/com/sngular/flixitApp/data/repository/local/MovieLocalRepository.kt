package com.sngular.flixitApp.data.repository.local

import com.sngular.flixitApp.data.model.dao.MovieDao
import com.sngular.flixitApp.data.model.entity.PopularMovieEntity
import com.sngular.flixitApp.data.model.entity.RateMovieEntity
import com.sngular.flixitApp.data.model.entity.UpcomingMovieEntity
import javax.inject.Inject

class MovieLocalRepository @Inject constructor(private val movieDao: MovieDao) {

    suspend fun getAllPopularMoviesLocal(): List<PopularMovieEntity> = movieDao.getAllPopularMovies()

    suspend fun insertAllPopularMovies(movies: List<PopularMovieEntity>) {
        movieDao.insertAllPopularMovies(movies)
    }

    suspend fun clearAllPopularMovies() {
        movieDao.deleteAllPopularMovies()
    }

    suspend fun getAllRateMoviesLocal(): List<RateMovieEntity> = movieDao.getAllRateMovies()

    suspend fun insertAllRateMovies(movies: List<RateMovieEntity>) {
        movieDao.insertAllRateMovies(movies)
    }

    suspend fun clearAllRateMovies() {
        movieDao.deleteAllRateMovies()
    }

    suspend fun getAllUpcomingMoviesLocal(): List<UpcomingMovieEntity> = movieDao.getAllUpcomingMovies()

    suspend fun insertAllUpcomingMovies(movies: List<UpcomingMovieEntity>) {
        movieDao.insertAllUpcomingMovies(movies)
    }

    suspend fun clearAllUpcomingMovies() {
        movieDao.deleteAllUpcomingMovies()
    }
}