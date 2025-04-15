package com.wilfredo.poke.module.di

import android.content.Context
import com.wilfredo.poke.module.data.repository.PokemonRepositoryImpl
import com.wilfredo.poke.module.domain.repository.PokemonRepository

object RepositoryProvider {
    fun providesPokemonRepository(context: Context): PokemonRepository = with(DataProvider) {
        PokemonRepositoryImpl(providesPokemonDb(context), providesPokemonApiService())
    }
}