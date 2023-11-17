package com.sngular.flixitApp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sngular.flixitApp.data.model.dto.KnownForDto
import com.sngular.flixitApp.domain.model.bo.KnownForBo
import com.sngular.flixitApp.domain.model.bo.PersonBo


@Entity(tableName = "person_table")
data class KnowForEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int? = 0,
    @ColumnInfo(name = "backdropPath") val backdropPath: String? = "",
    @ColumnInfo(name = "originalLanguage") val originalLanguage: Boolean = false,
    @ColumnInfo(name = "originalTitle") var originalTitle: String?,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "posterPath") val posterPath: String?,
    @ColumnInfo(name = "mediaType") val mediaType: String?,
    @ColumnInfo(name = "voteAverage") val voteAverage: Double?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "popularity") val popularity: Double?,
    @ColumnInfo(name = "voteCount") val voteCount: String?,
) : BaseEntity<KnownForBo> {
    override fun toBo(): KnownForBo {
        return KnownForBo(
            id = id,
            backdropPath = backdropPath,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
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