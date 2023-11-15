package com.sngular.flixitApp.data.model.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sngular.flixitApp.domain.model.bo.PersonBo
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonDto(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("gender") var gender: String? = null,
    @SerializedName("known_for_department") var knownForDepartment: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("original_name") var originalName: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("profile_path") var profilePath: String? = null,
    @SerializedName("known_for") var knownFor: ArrayList<KnownForDto> = arrayListOf(),
) : Parcelable, BaseDto<PersonBo> {
    override fun toBo(): PersonBo {
        return PersonBo(
            id = id,
            gender = gender.toString(),
            knownForDepartment = knownForDepartment,
            name = name,
            originalName = originalName,
            popularity = popularity!!.toString(),
            profilePath = profilePath,
            //knownFor = knownFor.map { it.toBo() },
        )
    }
}