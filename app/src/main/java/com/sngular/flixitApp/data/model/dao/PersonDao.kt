package com.sngular.flixitApp.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sngular.flixitApp.data.model.entity.PersonEntity
import com.sngular.flixitApp.data.model.entity.PopularMovieEntity
import com.sngular.flixitApp.data.model.entity.RateMovieEntity
import com.sngular.flixitApp.data.model.entity.UpcomingMovieEntity

@Dao
interface PersonDao {

    @Query("SELECT * FROM person_table")
    suspend fun getPersonInfo(): List<PersonEntity>

    @Insert(entity = PersonEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: List<PersonEntity>)

    @Query("DELETE FROM person_table")
    suspend fun deletePerson()
}