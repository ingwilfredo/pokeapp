package com.wilfredo.poke.module.di

import android.content.Context
import com.wilfredo.poke.module.ui.pokemonlist.PokemonListViewModel

object ModuleViewModelProvider {
    fun providesPokemonList(context: Context) = PokemonListViewModel(UseCaseProvider.providesGetPokemon(context))
}