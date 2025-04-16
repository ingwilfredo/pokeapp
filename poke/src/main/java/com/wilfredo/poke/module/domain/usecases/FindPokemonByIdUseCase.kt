package com.wilfredo.poke.module.domain.usecases

import com.wilfredo.poke.module.domain.model.Pokemon
import com.wilfredo.poke.module.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class FindPokemonByIdUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): Flow<Pokemon?> = pokemonRepository.findPokemonById(id)
}