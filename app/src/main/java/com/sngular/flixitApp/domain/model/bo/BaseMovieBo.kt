package com.sngular.flixitApp.domain.model.bo

import com.sngular.flixitApp.data.model.entity.PopularMovieEntity
import com.sngular.flixitApp.data.model.entity.RateMovieEntity
import com.sngular.flixitApp.data.model.entity.UpcomingMovieEntity

interface BaseMovieBo<T> {
    fun toPopularEntity(): PopularMovieEntity

    fun toRateEntity(): RateMovieEntity

    fun toUpcomingEntity(): UpcomingMovieEntity
    fun toBo(): T
}