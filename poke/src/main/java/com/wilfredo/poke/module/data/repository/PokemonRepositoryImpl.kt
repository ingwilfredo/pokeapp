package com.wilfredo.poke.module.data.repository

import com.wilfredo.poke.module.data.api.PokemonApiService
import com.wilfredo.poke.module.data.database.PokemonDatabase
import com.wilfredo.poke.module.data.database.model.TypePokemonEntity
import com.wilfredo.poke.module.data.performUpdateOperation
import com.wilfredo.poke.module.data.toPokemon
import com.wilfredo.poke.module.data.toPokemonDetail
import com.wilfredo.poke.module.data.toRoomPokemon
import com.wilfredo.poke.module.data.toRoomPokemonDetail
import com.wilfredo.poke.module.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PokemonRepositoryImpl(
    private val db: PokemonDatabase,
    private val apiService: PokemonApiService
) : PokemonRepository {

    override suspend fun getPokemonCount() = withContext(Dispatchers.IO) {
        db.pokemonDao().getPokemonCount()
    }

    override suspend fun getPokemon() = withContext(Dispatchers.IO) {
        db.pokemonDao().getPokemon().map { it.toPokemon() }
    }

    override suspend fun fetchPokemon(pageSize: Int, currentPage: Int) = withContext(Dispatchers.IO) {
        performUpdateOperation(
            {
                apiService.getPokemon(pageSize, currentPage)
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

    override suspend fun updatePokemonByName(name: String) = withContext(Dispatchers.IO) {
        performUpdateOperation(
            {
                apiService.getPokemonDetail(name)
            },
            { response ->
                response?.toRoomPokemonDetail()
            },
            { pokemon ->
                db.pokemonDao().updatePokemonByName(name, pokemon.pokemonId, pokemon.height, pokemon.weight)
                pokemon.types.map { type ->
                    db.typePokemonDao().insertTypePokemon(TypePokemonEntity(0, pokemon.pokemonId, type.name, type.url))
                }

                db.pokemonDao().findPokemonByName(name).map {
                    it.toPokemon()
                }
            }
        )
    }

    override suspend fun findPokemonById(id: Int) = withContext(Dispatchers.IO) {
        db.pokemonDao().findPokemonWithTypesById(id).map {
            it?.toPokemonDetail()
        }
    }

    override suspend fun togglePokemonFavorite(pokemonId: Int, isFavorite: Boolean) =
        withContext(Dispatchers.IO) {
            db.pokemonDao().toggleFavorite(pokemonId, isFavorite)
        }
}

