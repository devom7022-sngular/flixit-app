package com.sngular.flixitApp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sngular.flixitApp.domain.usecase.GetMoviesUseCase
import com.sngular.flixitApp.domain.model.bo.MovieBo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    val popularMovies = MutableLiveData<List<MovieBo>>()
    val rateMovies = MutableLiveData<List<MovieBo>>()
    val upcomingMovies = MutableLiveData<List<MovieBo>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val popular = getMoviesUseCase.getAllPopularMovies()
            val rated = getMoviesUseCase.getAllRateMovies()
            val upcoming = getMoviesUseCase.getAllUpcomingMovies()

            if (popular.isNotEmpty()) {
                popularMovies.postValue(popular)
            }

            if (rated.isNotEmpty()) {
                rateMovies.postValue(rated)
            }

            if (upcoming.isNotEmpty()) {
                upcomingMovies.postValue(upcoming)

            }

            isLoading.postValue(false)
        }
    }
}