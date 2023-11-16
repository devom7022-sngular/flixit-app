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
                parentColumns = ["KnownForId"],
                childColumns = ["FK_B"],
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
            ),
        ],
        primaryKeys = ["FK_A", "FK_B"]
    )
    data class AXYMap(
        val FK_A: Long,
        val FK_X: Long,
    )
}