package com.wilfredo.poke.module.domain.usecases

import com.wilfredo.poke.module.data.DataResult
import com.wilfredo.poke.module.domain.model.Pokemon
import com.wilfredo.poke.module.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdatePokemonByNameUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(name: String): Flow<DataResult<List<Pokemon>, Exception>> = flow {
        emit(DataResult.Loading)
        emit(pokemonRepository.updatePokemonByName(name))
    }
}