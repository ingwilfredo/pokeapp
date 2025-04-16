package com.wilfredo.poke.module.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonWithTypes(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "pokemonId"
    )
    val types: List<TypePokemonEntity>
)