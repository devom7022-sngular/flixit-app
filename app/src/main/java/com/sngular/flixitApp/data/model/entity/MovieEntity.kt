package com.sngular.flixitApp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sngular.flixitApp.domain.model.bo.MovieBo


@Entity(tableName = "movie_table")
class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int? = 0,
    @ColumnInfo(name = "title") val title: String? = "",
    @ColumnInfo(name = "video") val video: Boolean = false
) {
}

fun MovieBo.toDatabase() = MovieEntity(id = id, title = title, video = video)