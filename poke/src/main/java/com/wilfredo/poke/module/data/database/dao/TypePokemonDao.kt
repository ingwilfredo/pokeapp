package com.wilfredo.poke.module.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.wilfredo.poke.module.data.database.model.TypePokemonEntity

@Dao
interface TypePokemonDao : BaseDao<TypePokemonEntity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTypePokemon(location: TypePokemonEntity)
}