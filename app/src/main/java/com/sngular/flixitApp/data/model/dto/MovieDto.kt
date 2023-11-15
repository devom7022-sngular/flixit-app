package com.sngular.flixitApp.data.model.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sngular.flixitApp.domain.model.bo.MovieBo
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDto(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("video") var video: Boolean? = null,
    @SerializedName("adult") var adult: Boolean? = null,
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("genre_ids") var genreIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("original_language") var originalLanguage: String? = null,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null,
    @SerializedName("vote_count") var voteCount: Int? = null
) : Parcelable, BaseDto<MovieBo> {
    override fun toBo(): MovieBo {
        return MovieBo(
            id = id, title = title, video = video!!,
            adult = adult!!,
            backdropPath = backdropPath,
            originalLanguage = originalLanguage!!,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }
}

