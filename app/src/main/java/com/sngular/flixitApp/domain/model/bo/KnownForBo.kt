package com.sngular.flixitApp.domain.model.bo

import android.os.Parcelable
import com.sngular.flixitApp.data.model.entity.KnowForEntity
import kotlinx.parcelize.Parcelize

@Parcelize
class KnownForBo(
    val id: Int?,
    val backdropPath: String?,
    val originalLanguage: Boolean?,
    val originalTitle: String?,
    val overview: String?,
    val posterPath: String,
    val mediaType: String?,
    val voteAverage: Double?,
    val name: String?,
    val popularity: Double?,
    val voteCount: String?,

) : Parcelable, BaseBo<KnowForEntity> {
    override fun toEntity(): KnowForEntity {
        return KnowForEntity(
            id = id,
            backdropPath = backdropPath,
            originalLanguage = originalLanguage!!,
            originalTitle = originalTitle,
            overview = overview,
            posterPath = posterPath,
            mediaType = mediaType,
            voteAverage = voteAverage,
            name = name,
            popularity = popularity,
            voteCount = voteCount
        )
    }
}