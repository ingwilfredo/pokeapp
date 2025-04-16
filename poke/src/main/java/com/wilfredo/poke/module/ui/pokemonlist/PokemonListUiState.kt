package com.wilfredo.poke.module.ui.pokemonlist

import com.wilfredo.poke.module.domain.model.Pokemon

sealed class PokemonListUiState {
    data object Start: PokemonListUiState()
    data object Loading : PokemonListUiState()
    data class Success(val pokemon: List<Pokemon>) : PokemonListUiState()
    data object Error : PokemonListUiState()
}