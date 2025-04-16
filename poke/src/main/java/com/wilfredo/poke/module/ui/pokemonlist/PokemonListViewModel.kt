package com.wilfredo.poke.module.ui.pokemonlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilfredo.poke.module.data.DataResult
import com.wilfredo.poke.module.domain.usecases.GetPokemonUseCase
import com.wilfredo.poke.module.domain.usecases.TogglePokemonFavoriteUseCase
import com.wilfredo.poke.module.domain.usecases.UpdatePokemonByNameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val togglePokemonFavoriteUseCase: TogglePokemonFavoriteUseCase,
    private val updatePokemonByNameUseCase: UpdatePokemonByNameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PokemonListUiState>(PokemonListUiState.Start)
    val uiState = _uiState.asStateFlow()


    fun getPokemon(pageSize: Int, currentPage: Int) {
        viewModelScope.launch {
            getPokemonUseCase(pageSize, currentPage).collect { result ->
                when (result) {
                    DataResult.Loading -> _uiState.update {PokemonListUiState.Loading }
                    is DataResult.Success -> {
                        result.data.forEach { pokemon ->
                            getPokemonDetail(pokemon.name)
                        }
                        _uiState.update { PokemonListUiState.Success(result.data) }
                    }

                    is DataResult.Error -> _uiState.update {
                        PokemonListUiState.Error
                    }
                }
            }
        }
    }

    private fun getPokemonDetail(name: String) {
        viewModelScope.launch {
            updatePokemonByNameUseCase(name).collect {
            }
        }
    }

    fun toggleFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            togglePokemonFavoriteUseCase(id, !isFavorite)
        }
    }
}