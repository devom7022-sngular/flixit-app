package com.sngular.flixitApp.domain.model.bo

interface BaseBo<T> {
    fun toEntity() : T
}