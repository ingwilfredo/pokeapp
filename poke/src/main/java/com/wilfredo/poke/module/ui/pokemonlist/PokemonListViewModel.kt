package com.wilfredo.poke.module.ui.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilfredo.poke.module.data.DataResult
import com.wilfredo.poke.module.domain.usecases.GetPokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonListViewModel(private val getPokemonUseCase: GetPokemonUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<PokemonListUiState>(PokemonListUiState.Start)
    val uiState = _uiState.asStateFlow()

    fun getPokemon() {
        viewModelScope.launch {
            getPokemonUseCase().collect { result ->
                when (result) {
                    DataResult.Loading -> _uiState.value = PokemonListUiState.Loading
                    is DataResult.Success -> _uiState.value = PokemonListUiState.Content(result.data)
                    is DataResult.Error -> _uiState.value = PokemonListUiState.Error
                }
            }
        }
    }
}