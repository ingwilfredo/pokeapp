package com.wilfredo.poke.module.domain.usecases

import com.wilfredo.poke.module.data.DataResult
import com.wilfredo.poke.module.domain.model.Pokemon
import com.wilfredo.poke.module.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(): Flow<DataResult<List<Pokemon>, Exception>> = flow {
        if (pokemonRepository.getPokemonCount() == 0) {
            emit(DataResult.Loading)
            emit(pokemonRepository.fetchPokemon())
        } else {
            emit(DataResult.Success(pokemonRepository.getPokemon()))
        }
    }
}