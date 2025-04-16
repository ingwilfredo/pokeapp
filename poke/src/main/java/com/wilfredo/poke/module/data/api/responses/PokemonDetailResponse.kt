package com.wilfredo.poke.module.data.api.responses

data class PokemonDetailResponse(
    val id: Int,
    val height: Int,
    val weight: Int,
    val types: List<TypeListPokemonResponse>,
)


data class TypeListPokemonResponse(
    val type: TypePokemonResponse
)

data class TypePokemonResponse(
    val name: String,
    val url: String
)