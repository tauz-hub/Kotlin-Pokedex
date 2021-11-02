package br.com.tauasanto.pokedexandroidkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.tauasanto.pokedexandroidkotlin.api.PokemonRepository
import br.com.tauasanto.pokedexandroidkotlin.domain.PokemonArrays

class PokemonViewModel : ViewModel() {
    var pokemons = MutableLiveData<List<PokemonArrays?>>()
    init {
        Thread(Runnable {
            loadPokemons()
        }).start()

    }
    private fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let {

            pokemons.postValue(  it.map { pokemonResult ->
                val number: Int = pokemonResult.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "")
                    .toInt()

                val pokemonApiResult = PokemonRepository.getPokemon(number)

                pokemonApiResult?.let {
                    PokemonArrays(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map { type ->
                            type.type
                        }
                    )
                }
            })
        }
    }
}