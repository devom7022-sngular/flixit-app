package com.sngular.flixitApp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sngular.flixitApp.domain.usecase.GetMoviesUseCase
import com.sngular.flixitApp.domain.model.bo.MovieBo
import com.sngular.flixitApp.domain.model.bo.PersonBo
import com.sngular.flixitApp.domain.usecase.GetPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val getPersonUseCase: GetPersonUseCase
) : ViewModel() {

    val personData = MutableLiveData<List<PersonBo>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val person = getPersonUseCase.getPersonInfo()
            personData.postValue(person)

            isLoading.postValue(false)
        }
    }
}