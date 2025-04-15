package com.wilfredo.poke.module.domain.repository

import com.wilfredo.poke.module.data.DataResult
import com.wilfredo.poke.module.domain.model.Pokemon

interface PokemonRepository {

    suspend fun getPokemonCount(): Int
    suspend fun getPokemon(): List<Pokemon>
    suspend fun fetchPokemon(): DataResult<List<Pokemon>, Exception>
}