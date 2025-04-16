package com.wilfredo.poke.module.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.wilfredo.poke.module.data.database.model.PokemonEntity
import com.wilfredo.poke.module.data.database.model.PokemonWithTypes
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao : BaseDao<PokemonEntity> {
    @Query("SELECT COUNT(id) FROM Pokemon")
    fun getPokemonCount(): Int

    @Query("SELECT * FROM Pokemon")
    suspend fun getPokemon(): List<PokemonEntity>

    @Query("SELECT * FROM Pokemon WHERE id=:id")
    fun findPokemonById(id: Int): Flow<PokemonEntity?>

    @Transaction
    @Query("SELECT * FROM Pokemon WHERE Pokemon.id=:id")
    fun findPokemonWithTypesById(id: Int): Flow<PokemonWithTypes?>

    @Query("SELECT * FROM Pokemon WHERE name=:name")
    fun findPokemonByName(name: String): List<PokemonEntity>

    @Query("UPDATE Pokemon SET favorite=:isFavorite WHERE id=:id")
    fun toggleFavorite(id: Int, isFavorite: Boolean)

    @Query("UPDATE Pokemon SET pokemonId=:pokemonId, height=:height, weight=:weight WHERE name=:name")
    fun updatePokemonByName(name: String, pokemonId: Int, height: Int, weight: Int)
}