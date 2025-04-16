package com.wilfredo.poke.module.di

import android.content.Context
import com.wilfredo.poke.module.di.UseCaseProvider.providesTogglePokemonFavorite
import com.wilfredo.poke.module.di.UseCaseProvider.providesUpdatePokemonByName
import com.wilfredo.poke.module.ui.pokemondetail.PokemonDetailViewModel
import com.wilfredo.poke.module.ui.pokemonlist.PokemonListViewModel

object ModuleViewModelProvider {
    fun providesPokemonList(context: Context) = PokemonListViewModel(
        UseCaseProvider.providesGetPokemon(context),
        providesTogglePokemonFavorite(context),
        providesUpdatePokemonByName(context)
    )

    fun providesPokemonDetail(context: Context) = with(UseCaseProvider) {
        PokemonDetailViewModel(
            providesFindPokemonById(context),
            providesTogglePokemonFavorite(context)
        )
    }
}