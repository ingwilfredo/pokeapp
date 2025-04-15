package com.wilfredo.poke.module.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.wilfredo.poke.module.data.database.model.PokemonEntity

@Dao
interface PokemonDao : BaseDao<PokemonEntity> {
    @Query("SELECT COUNT(id) FROM Pokemon")
    fun getPokemonCount(): Int

    @Query("SELECT * FROM Pokemon")
    suspend fun getPokemon(): List<PokemonEntity>
}