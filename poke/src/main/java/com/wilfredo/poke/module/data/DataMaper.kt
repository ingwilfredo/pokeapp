package com.wilfredo.poke.module.data

import com.wilfredo.poke.module.data.api.responses.PokemonResponse
import com.wilfredo.poke.module.data.database.model.PokemonEntity
import com.wilfredo.poke.module.domain.model.Pokemon


fun PokemonEntity.toPokemon(): Pokemon = Pokemon(
    id,
    name
)
fun PokemonResponse.toRoomPokemon(): PokemonEntity = PokemonEntity(
    0,
    name,
    url
)