package com.wilfredo.poke.module.domain.repository

import com.wilfredo.poke.module.data.DataResult
import com.wilfredo.poke.module.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonCount(): Int

    suspend fun getPokemon(): List<Pokemon>

    suspend fun fetchPokemon(pageSize: Int, currentPage: Int): DataResult<List<Pokemon>, Exception>

    suspend fun findPokemonById(id: Int): Flow<Pokemon?>

    suspend fun updatePokemonByName(name: String): DataResult<List<Pokemon>, Exception>

    suspend fun togglePokemonFavorite(pokemonId: Int, isFavorite: Boolean)

}