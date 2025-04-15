package com.wilfredo.poke.module.data.repository

import com.wilfredo.poke.module.data.api.PokemonApiService
import com.wilfredo.poke.module.data.database.PokemonDatabase
import com.wilfredo.poke.module.data.performUpdateOperation
import com.wilfredo.poke.module.data.toPokemon
import com.wilfredo.poke.module.data.toRoomPokemon
import com.wilfredo.poke.module.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepositoryImpl(
    private val db: PokemonDatabase,
    private val apiService: PokemonApiService
) : PokemonRepository {

    override suspend fun getPokemonCount() = withContext(Dispatchers.IO) {
        db.pokemonDao().getPokemonCount()
    }

    override suspend fun getPokemon()= withContext(Dispatchers.IO) {
        db.pokemonDao().getPokemon().map { it.toPokemon() }
    }

    override suspend fun fetchPokemon() = withContext(Dispatchers.IO) {
        performUpdateOperation(
            {
                apiService.getPokemon(20, 100)
            },
            { response ->
                response?.results?.map { it.toRoomPokemon() }
            },
            { pokemon ->
                db.pokemonDao().insertBulk(pokemon)
                db.pokemonDao().getPokemon().map { it.toPokemon() }
            }
        )
    }
}