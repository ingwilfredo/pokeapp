package com.wilfredo.poke.module.data

import com.wilfredo.poke.module.data.api.responses.PokemonDetailResponse
import com.wilfredo.poke.module.data.api.responses.PokemonResponse
import com.wilfredo.poke.module.data.database.model.PokemonDetail
import com.wilfredo.poke.module.data.database.model.PokemonEntity
import com.wilfredo.poke.module.domain.model.Pokemon


fun PokemonEntity.toPokemon(): Pokemon = Pokemon(
    id,
    pokemonId,
    name,
    url,
    height,
    weight,
    favorite
)

fun PokemonResponse.toRoomPokemon(): PokemonEntity = PokemonEntity(
    0,
    0,
    name,
    url,
    0,
    0,
    false
)

fun PokemonDetailResponse.toRoomPokemonDetail(): PokemonDetail = PokemonDetail(
    id,
    height,
    weight
)