package com.wilfredo.poke.module.di

import android.content.Context
import com.wilfredo.poke.module.di.DataProvider.providesConnectionHelper
import com.wilfredo.poke.module.domain.usecases.FindPokemonByIdUseCase
import com.wilfredo.poke.module.domain.usecases.GetPokemonUseCase
import com.wilfredo.poke.module.domain.usecases.TogglePokemonFavoriteUseCase
import com.wilfredo.poke.module.domain.usecases.UpdatePokemonByNameUseCase

object UseCaseProvider {
    fun providesGetPokemon(context: Context): GetPokemonUseCase =
        GetPokemonUseCase(RepositoryProvider.providesPokemonRepository(context), providesConnectionHelper(context))

    fun providesFindPokemonById(context: Context): FindPokemonByIdUseCase =
        FindPokemonByIdUseCase(RepositoryProvider.providesPokemonRepository(context))

    fun providesTogglePokemonFavorite(context: Context): TogglePokemonFavoriteUseCase =
        TogglePokemonFavoriteUseCase(RepositoryProvider.providesPokemonRepository(context))

    fun providesUpdatePokemonByName(context: Context) =
        UpdatePokemonByNameUseCase(RepositoryProvider.providesPokemonRepository(context))
}