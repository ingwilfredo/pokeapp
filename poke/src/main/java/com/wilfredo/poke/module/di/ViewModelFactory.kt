package com.wilfredo.poke.module.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wilfredo.poke.module.ui.pokemonlist.PokemonListViewModel

class ViewModelFactory(
    private val context: Context,
    private val moduleViewModel: ModuleViewModel
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (!modelClass.isAssignableFrom(moduleViewModel.clazz)) throw Exception()
        val appContext = context.applicationContext
        return when(moduleViewModel) {
            ModuleViewModel.PokemonList -> ModuleViewModelProvider.providesPokemonList(appContext)
        } as T
    }
}

enum class ModuleViewModel(val clazz: Class<*>) {
    PokemonList(PokemonListViewModel::class.java)
}