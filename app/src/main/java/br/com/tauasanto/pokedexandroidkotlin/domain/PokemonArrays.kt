package br.com.tauasanto.pokedexandroidkotlin.domain

data class PokemonArrays (
    val number: Int,
    val name: String,
    val types: List<PokemonType>,
    ) {
    val formattedName = name.capitalize()

    val formattedNumber = number.toString().padStart(3, '0')
    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$formattedNumber.png"
}
