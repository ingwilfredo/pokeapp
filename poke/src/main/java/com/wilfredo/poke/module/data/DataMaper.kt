package com.wilfredo.poke.module.data

import com.wilfredo.poke.module.data.api.responses.PokemonDetailResponse
import com.wilfredo.poke.module.data.api.responses.PokemonResponse
import com.wilfredo.poke.module.data.api.responses.TypePokemonResponse
import com.wilfredo.poke.module.data.database.model.PokemonDetail
import com.wilfredo.poke.module.data.database.model.PokemonEntity
import com.wilfredo.poke.module.data.database.model.PokemonWithTypes
import com.wilfredo.poke.module.domain.model.Pokemon
import com.wilfredo.poke.module.domain.model.TypePokemon


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
    weight,
    types = this.types.map { it.type.toTypePokemon() },
)

fun TypePokemonResponse.toTypePokemon(): TypePokemon = TypePokemon(
    0,
    name,
    url
)

fun PokemonWithTypes.toPokemonDetail(): Pokemon {
    return Pokemon(
        id = pokemon.id,
        name = pokemon.name,
        url = pokemon.url,
        height = pokemon.height,
        weight = pokemon.weight,
        isFavorite = pokemon.favorite,
        types = types.map { it.name }
    )
}