package com.wilfredo.poke.module.data.api.responses

data class PokemonPagesResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results : List<PokemonResponse>
)

data class PokemonResponse(
    val name: String,
    val url: String
)

