package com.wilfredo.poke.module.data.api

import com.wilfredo.poke.module.data.api.responses.PokemonPagesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApiService {

    @GET("ability")
    suspend fun getPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonPagesResponse>
}