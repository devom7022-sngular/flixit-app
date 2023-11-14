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
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getMoviesUseCase()

            if (result.isNotEmpty()) {
                popularMovies.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}