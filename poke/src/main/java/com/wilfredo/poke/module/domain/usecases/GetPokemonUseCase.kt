package com.wilfredo.poke.module.domain.usecases

import com.wilfredo.poke.module.data.DataResult
import com.wilfredo.poke.module.domain.model.Pokemon
import com.wilfredo.poke.module.domain.repository.PokemonRepository
import com.wilfredo.poke.module.utils.ConnectionHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPokemonUseCase(
    private val pokemonRepository: PokemonRepository,
    private val connectionHelper: ConnectionHelper
) {
    operator fun invoke(pageSize: Int, currentPage: Int): Flow<DataResult<List<Pokemon>, Exception>> = flow {
        connectionHelper.hasInternet()
        if (connectionHelper.hasInternet()) {
            emit(DataResult.Loading)
            emit(pokemonRepository.fetchPokemon(pageSize, currentPage))
        } else {
            emit(DataResult.Success(pokemonRepository.getPokemon()))
        }
    }
}