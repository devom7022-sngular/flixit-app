package com.sngular.flixitApp.domain.model.bo

import com.sngular.flixitApp.data.model.entity.MovieEntity
import com.sngular.flixitApp.data.model.dto.MovieDto

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
)

fun MovieDto.toBo() = MovieBo(
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

fun MovieEntity.toBo() = MovieBo(
    id, title, video, adult,
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