package com.wilfredo.poke.module.data.database.model

import com.wilfredo.poke.module.domain.model.TypePokemon


data class PokemonDetail(
    val pokemonId: Int,
    val height: Int,
    val weight: Int,
    val types: List<TypePokemon>
)
