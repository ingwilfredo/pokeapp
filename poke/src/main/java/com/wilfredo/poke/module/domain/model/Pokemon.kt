package com.wilfredo.poke.module.domain.model

data class Pokemon(
    val id: Int = 0,
    val pokemonId: Int = 0,
    val name: String = "",
    val url: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    var isFavorite: Boolean = false
)
