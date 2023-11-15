package com.sngular.flixitApp.domain.model.bo

import android.os.Parcelable
import com.sngular.flixitApp.data.model.entity.MovieEntity

import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieBo(
    val id: Int?,
    val title: String?,
    val video: Boolean,
    val adult: Boolean,
    val backdropPath: String?,
    val originalLanguage: String,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val voteAverage: Double?,
    val voteCount: Int?
) : Parcelable, BaseBo<MovieEntity> {
    override fun toEntity(): MovieEntity {
        return MovieEntity(
            id, title, video!!, adult!!,
            backdropPath,
            originalLanguage!!,
            originalTitle,
            overview,
            popularity,
            posterPath,
            releaseDate,
            voteAverage,
            voteCount
        )
    }
}