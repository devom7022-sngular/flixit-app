package com.sngular.flixitApp.data.model.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sngular.flixitApp.domain.model.bo.KnownForBo
import com.sngular.flixitApp.domain.model.bo.PersonBo
import kotlinx.parcelize.Parcelize

@Parcelize
data class KnownForDto(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("original_language") var originalLanguage: Boolean? = null,
    @SerializedName("original_title") var originalTitle: Boolean? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("media_type") var mediaType: String? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("vote_count") var voteCount: String? = null,
) : Parcelable, BaseDto<KnownForBo> {
    override fun toBo(): KnownForBo {
        return KnownForBo(
            id = id,
            backdropPath = backdropPath,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle?.toString(),
            overview = overview,
            posterPath = posterPath!!,
            mediaType = mediaType,
            voteAverage = voteAverage,
            name = name,
            popularity = popularity,
            voteCount = voteCount

        )
    }
}