package com.wilfredo.poke.module.data.database

import android.content.Context
import androidx.room.Room

object PokemonDatabaseBuilder {
    private const val DB_NAME = "Pokemon.db"
    @Volatile
    private var INSTANCE: PokemonDatabase? = null

    fun getInstance(context: Context): PokemonDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = buildRoom(context)
            INSTANCE = instance
            instance
        }
    }

    private fun buildRoom(context: Context): PokemonDatabase {
        return Room.databaseBuilder(context, PokemonDatabase::class.java, DB_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}