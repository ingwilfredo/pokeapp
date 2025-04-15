package com.wilfredo.poke.module.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wilfredo.poke.module.data.database.dao.PokemonDao
import com.wilfredo.poke.module.data.database.model.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = true)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}