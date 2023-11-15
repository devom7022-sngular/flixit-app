package com.sngular.flixitApp.data.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey

class PersonRelationEntity {
    @Entity(
        foreignKeys = [
            ForeignKey(
                entity = PersonEntity::class,
                parentColumns = ["personId"],
                childColumns = ["FK_A"],
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
            ),
            ForeignKey(
                entity = KnowForEntity::class,
                parentColumns = ["xId"],
                childColumns = ["FK_X"],
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
            ),
        ],
        primaryKeys = ["FK_A","FK_X","FK_Y"]
    )
    data class AXYMap(
        val FK_A: Long,
        val FK_X: Long,
        val FK_Y: Long
    )
}