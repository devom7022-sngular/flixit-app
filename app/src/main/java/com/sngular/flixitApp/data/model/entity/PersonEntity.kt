package com.sngular.flixitApp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sngular.flixitApp.domain.model.bo.PersonBo


@Entity(tableName = "person_table")
data class PersonEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int? = 0,
    @ColumnInfo(name = "gender") val gender: String? = "",
    @ColumnInfo(name = "knownForDepartment") val knownForDepartment: String?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "originalName") val originalName: String?,
    @ColumnInfo(name = "popularity") val popularity: String?,
    @ColumnInfo(name = "profilePath") val profilePath: String?,
    //@ColumnInfo(name = "knownFor") val knownFor: List<KnowForEntity>? = null,
) : BaseEntity<PersonBo> {
    override fun toBo(): PersonBo {
        return PersonBo(
            id = id,
            gender = gender,
            knownForDepartment = knownForDepartment.toString(),
            name = name.toString(),
            originalName = originalName,
            popularity = popularity!!,
            profilePath = profilePath,
            //knownFor = knownFor?.map { it.toBo() }!!,
        )
    }
}