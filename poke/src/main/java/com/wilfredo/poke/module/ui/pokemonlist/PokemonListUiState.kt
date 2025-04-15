package com.wilfredo.poke.module.ui.pokemonlist

import com.wilfredo.poke.module.domain.model.Pokemon

sealed class PokemonListUiState {
    data object Start: PokemonListUiState()
    data object Loading : PokemonListUiState()
    data class Content(val movies: List<Pokemon>) : PokemonListUiState()
    data object Error : PokemonListUiState()
}