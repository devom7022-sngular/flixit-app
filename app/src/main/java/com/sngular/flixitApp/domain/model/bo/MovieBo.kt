package com.sngular.flixitApp.domain.model.bo

import android.os.Parcelable
import com.sngular.flixitApp.data.model.entity.PopularMovieEntity
import com.sngular.flixitApp.data.model.entity.RateMovieEntity
import com.sngular.flixitApp.data.model.entity.UpcomingMovieEntity

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
) : Parcelable, BaseMovieBo<MovieBo> {
    override fun toPopularEntity(): PopularMovieEntity {
        return PopularMovieEntity(
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

    override fun toRateEntity(): RateMovieEntity {
        return RateMovieEntity(
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

    override fun toUpcomingEntity(): UpcomingMovieEntity {
        return UpcomingMovieEntity(
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


    override fun toBo(): MovieBo {
        return MovieBo(
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