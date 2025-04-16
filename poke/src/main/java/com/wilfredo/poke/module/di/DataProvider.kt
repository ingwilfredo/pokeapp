package com.wilfredo.poke.module.di

import android.content.Context
import com.wilfredo.poke.module.common.Constants.BASE_URL
import com.wilfredo.poke.module.data.api.PokemonApiService
import com.wilfredo.poke.module.data.database.PokemonDatabase
import com.wilfredo.poke.module.data.database.PokemonDatabaseBuilder
import com.wilfredo.poke.module.utils.ConnectionHelper

object DataProvider {
    fun providesConnectionHelper(context: Context): ConnectionHelper =
        ConnectionHelper.getInstance(context)

    fun providesPokemonDb(context: Context): PokemonDatabase =
        PokemonDatabaseBuilder.getInstance(context)

    fun providesPokemonApiService(): PokemonApiService =
        ApiServiceProvider.buildApiService(
            PokemonApiService::class.java,
            BASE_URL,
            30
        )
}