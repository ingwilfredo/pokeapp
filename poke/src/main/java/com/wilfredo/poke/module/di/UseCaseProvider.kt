package com.wilfredo.poke.module.di

import android.content.Context
import com.wilfredo.poke.module.domain.usecases.GetPokemonUseCase

object UseCaseProvider {
    fun providesGetPokemon(context: Context): GetPokemonUseCase =
        GetPokemonUseCase(RepositoryProvider.providesPokemonRepository(context))
}