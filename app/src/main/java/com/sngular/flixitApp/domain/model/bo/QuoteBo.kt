package com.sngular.flixitApp.domain.model.bo

import com.sngular.flixitApp.data.model.entity.MovieEntity
import com.sngular.flixitApp.data.model.dto.MovieDto

data class MovieBo(val id: Int?, val title: String?, val video: Boolean)

fun MovieDto.toDomain() = MovieBo(id, title, video!!)
fun MovieEntity.toDomain() = MovieBo(id, title , video)