package com.sngular.flixitApp.domain.usecase

import com.sngular.flixitApp.data.repository.MovieRepository
import com.sngular.flixitApp.data.repository.PersonRepository
import com.sngular.flixitApp.domain.model.bo.MovieBo
import com.sngular.flixitApp.domain.model.bo.PersonBo

import javax.inject.Inject

class GetPersonUseCase @Inject constructor(private val repository: PersonRepository) {
    suspend fun getPersonInfo(): List<PersonBo> {
        return repository.getPersonInfo()
    }
}