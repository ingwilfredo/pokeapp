package com.wilfredo.poke.module.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wilfredo.poke.module.data.database.dao.LocationDao
import com.wilfredo.poke.module.data.database.dao.PokemonDao
import com.wilfredo.poke.module.data.database.dao.TypePokemonDao
import com.wilfredo.poke.module.data.database.model.LocationEntity
import com.wilfredo.poke.module.data.database.model.PokemonEntity
import com.wilfredo.poke.module.data.database.model.TypePokemonEntity

@Database(entities = [PokemonEntity::class, LocationEntity::class, TypePokemonEntity::class], version = 1, exportSchema = true)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun locationDao(): LocationDao
    abstract fun typePokemonDao(): TypePokemonDao
}