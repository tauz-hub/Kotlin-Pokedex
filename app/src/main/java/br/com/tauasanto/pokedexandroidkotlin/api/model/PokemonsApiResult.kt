package br.com.tauasanto.pokedexandroidkotlin.api.model

import br.com.tauasanto.pokedexandroidkotlin.domain.PokemonType

data class PokemonsApiResult(
    val count: Int,
    val next: String?,
    val previus: String?,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)

data class PokemonApiResult(
    val name: String,
    val types: PokemonTypeSlot
)

data class PokemonTypeSlot (
    val slot: Int,
    val type: PokemonType
)
