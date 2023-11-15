package com.sngular.flixitApp.data.repository.local

import com.sngular.flixitApp.data.model.dao.PopularMovieDao
import com.sngular.flixitApp.data.model.dao.RateMovieDao
import com.sngular.flixitApp.data.model.dao.UpcomingMovieDao
import com.sngular.flixitApp.data.model.entity.PopularMovieEntity
import com.sngular.flixitApp.data.model.entity.RateMovieEntity
import com.sngular.flixitApp.data.model.entity.UpcomingMovieEntity
import javax.inject.Inject

class MovieLocalRepository @Inject constructor(
    private val popularMovieDao: PopularMovieDao,
    private val rateMovieDao: RateMovieDao,
    private val upcomingMovieDao: UpcomingMovieDao
) {

    suspend fun getAllPopularMoviesLocal(): List<PopularMovieEntity> =
        popularMovieDao.getAllPopularMovies()

    suspend fun insertAllPopularMovies(movies: List<PopularMovieEntity>) {
        popularMovieDao.insertAllPopularMovies(movies)
    }

    suspend fun clearAllPopularMovies() {
        popularMovieDao.deleteAllPopularMovies()
    }

    suspend fun getAllRateMoviesLocal(): List<RateMovieEntity> = rateMovieDao.getAllRateMovies()

    suspend fun insertAllRateMovies(movies: List<RateMovieEntity>) {
        rateMovieDao.insertAllRateMovies(movies)
    }

    suspend fun clearAllRateMovies() {
        rateMovieDao.deleteAllRateMovies()
    }

    suspend fun getAllUpcomingMoviesLocal(): List<UpcomingMovieEntity> =
        upcomingMovieDao.getAllUpcomingMovies()

    suspend fun insertAllUpcomingMovies(movies: List<UpcomingMovieEntity>) {
        upcomingMovieDao.insertAllUpcomingMovies(movies)
    }

    suspend fun clearAllUpcomingMovies() {
        upcomingMovieDao.deleteAllUpcomingMovies()
    }
}