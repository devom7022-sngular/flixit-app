package com.sngular.flixitApp.domain.model.bo

import android.os.Parcelable
import com.sngular.flixitApp.data.model.entity.PersonEntity
import kotlinx.parcelize.Parcelize

@Parcelize
class PersonBo(
    val id: Int?,
    val gender: String?,
    val knownForDepartment: String?,
    val name: String?,
    val originalName: String?,
    val popularity: String,
    val profilePath: String?,
    //val knownFor: List<KnownForBo> = arrayListOf(),
) : Parcelable, BaseBo<PersonEntity> {
    override fun toEntity(): PersonEntity {
        return PersonEntity(
            id = id,
            gender = gender,
            knownForDepartment = knownForDepartment,
            name = name,
            originalName = originalName,
            popularity = popularity,
            profilePath = profilePath,
            //knownFor = knownFor.map { it.toEntity() },
        )
    }
}