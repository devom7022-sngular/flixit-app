package com.sngular.flixitApp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sngular.flixitApp.domain.model.bo.MovieBo


@Entity(tableName = "movie_table")
class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int? = 0,
    @ColumnInfo(name = "title") val title: String? = "",
    @ColumnInfo(name = "video") val video: Boolean = false,
    @ColumnInfo("adult") var adult: Boolean = false,
    @ColumnInfo(name = "backdropPath") val backdropPath: String? = "",
    @ColumnInfo(name = "originalLanguage") val originalLanguage: String,
    @ColumnInfo(name = "originalTitle") val originalTitle: String?,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "popularity") val popularity: Double?,
    @ColumnInfo(name = "posterPath") val posterPath: String?,
    @ColumnInfo(name = "releaseDate") val releaseDate: String?,
    @ColumnInfo(name = "voteAverage") val voteAverage: Double?,
    @ColumnInfo(name = "voteCount") val voteCount: Int?
)

fun MovieBo.toDatabase() = MovieEntity(
    id = id, title = title, video = video,
    adult = adult,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount
)