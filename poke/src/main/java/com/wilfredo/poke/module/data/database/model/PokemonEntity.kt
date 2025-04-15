package com.wilfredo.poke.module.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pokemon")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val url: String
)
