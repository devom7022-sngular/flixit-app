package com.sngular.flixitApp.data.repository

import com.sngular.flixitApp.data.repository.local.MovieLocalRepository
import com.sngular.flixitApp.data.repository.remote.MovieRemoteRepository
import com.sngular.flixitApp.domain.model.bo.PersonBo
import com.sngular.flixitApp.util.Utils
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val remote: MovieRemoteRepository,
    private val local: MovieLocalRepository,
    private val utils: Utils,
) {
    private suspend fun getRemotePersonInfo(): List<PersonBo> =
        remote.getPersonRemote().results!!.map { it.toBo() }


    private suspend fun getLocalPersonInfo(): List<PersonBo> =
        local.getPersonLocal().map { it.toBo() }

    suspend fun getPersonInfo(): List<PersonBo> {
        var person: List<PersonBo>
        if (utils.isConnectedToInternet()) {
            local.clearPerson()
            person = getRemotePersonInfo()
            local.insertPerson(person.map { it.toEntity() })
        } else {
            person = getLocalPersonInfo()
        }
        return person
    }
}