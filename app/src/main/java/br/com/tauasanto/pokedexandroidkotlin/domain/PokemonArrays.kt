package br.com.tauasanto.pokedexandroidkotlin.domain

data class PokemonArrays (
    val image: String,
    val number: Int,
    val name: String,
    val types: List<PokemonType>

){
    val formattedNumber  = number.toString().padStart(3, '0')
}
