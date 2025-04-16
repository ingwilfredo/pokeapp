package com.wilfredo.poke.module.domain.usecases

import com.wilfredo.poke.module.domain.repository.PokemonRepository

class TogglePokemonFavoriteUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(pokemonId: Int, isFavorite: Boolean) {
        pokemonRepository.togglePokemonFavorite(pokemonId, isFavorite)
    }
}