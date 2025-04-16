package com.wilfredo.poke.module.data.api

import com.wilfredo.poke.module.common.Constants.POKEMON
import com.wilfredo.poke.module.common.Constants.POKEMON_NAME
import com.wilfredo.poke.module.data.api.responses.PokemonDetailResponse
import com.wilfredo.poke.module.data.api.responses.PokemonPagesResponse
import com.wilfredo.poke.module.data.api.responses.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET(POKEMON)
    suspend fun getPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonPagesResponse>

    @GET(POKEMON_NAME)
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): Response<PokemonDetailResponse>
}