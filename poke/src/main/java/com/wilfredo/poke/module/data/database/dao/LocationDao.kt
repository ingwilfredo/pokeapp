package com.wilfredo.poke.module.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wilfredo.poke.module.data.database.model.LocationEntity
import com.wilfredo.poke.module.data.database.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao : BaseDao<LocationEntity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(location: LocationEntity)
}