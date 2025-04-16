package com.wilfredo.poke.module.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Type")
data class TypePokemonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val pokemonId: Int,
    val name: String,
    val url: String
)
