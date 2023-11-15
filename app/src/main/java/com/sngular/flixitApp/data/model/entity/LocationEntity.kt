package com.sngular.flixitApp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sngular.flixitApp.domain.model.bo.LocationBo
import com.sngular.flixitApp.domain.model.bo.MovieBo
import java.util.UUID

@Entity(tableName = "location_table")
class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int? = 0,
    @ColumnInfo(name = "long") var long: String? = "",
    @ColumnInfo(name = "lat") var lat: String? = "",
    @ColumnInfo(name = "register") var register: String? = "",
) : BaseEntity<LocationBo> {
    override fun toBo(): LocationBo {
        return LocationBo(
            long = long!!, lat = lat!!, register = register!!
        )
    }
}

