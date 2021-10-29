package br.com.tauasanto.pokedexandroidkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.tauasanto.pokedexandroidkotlin.R
import br.com.tauasanto.pokedexandroidkotlin.api.PokemonRepository
import br.com.tauasanto.pokedexandroidkotlin.domain.PokemonArrays
import br.com.tauasanto.pokedexandroidkotlin.domain.PokemonType

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rvPokemons)

//        val chamander = PokemonArrays(
//            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png",
//            4,
//            "Chamander",
//            listOf( PokemonType("Fire") )
//        )
//        val pokemonsViews = listOf(chamander,chamander,chamander,chamander,chamander)


        Thread(Runnable {
            loadPokemons()
        }).start()


    }

    private fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons()



        pokemonsApiResult?.results?.let {

            val pokemons: List<PokemonArrays?> = it.map { pokemonResult ->
                val number = pokemonResult.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
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
            }


            val layoutManager = LinearLayoutManager(this)

            recyclerView.post {
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = PokemonAdapter(pokemons)
            }

        }

    }

}