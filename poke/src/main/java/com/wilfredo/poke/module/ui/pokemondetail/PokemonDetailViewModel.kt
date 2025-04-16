package com.wilfredo.poke.module.ui.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilfredo.poke.module.domain.model.Pokemon
import com.wilfredo.poke.module.domain.usecases.FindPokemonByIdUseCase
import com.wilfredo.poke.module.domain.usecases.TogglePokemonFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val findPokemonByIdUseCase: FindPokemonByIdUseCase,
    private val togglePokemonFavoriteUseCase: TogglePokemonFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(Pokemon())
    val uiState = _uiState.asStateFlow()


    fun getPokemonDetail(pokemonId: Int) {
        viewModelScope.launch {
            findPokemonByIdUseCase(pokemonId).collect { pokemon ->
                pokemon?.let { _uiState.update { pokemon } }
            }
        }
    }


    fun toggleFavorite() {
        viewModelScope.launch {
            _uiState.value.copy(isFavorite = !_uiState.value.isFavorite).run {
                togglePokemonFavoriteUseCase(id, isFavorite)
            }
        }
    }
}