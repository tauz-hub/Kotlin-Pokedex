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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.rvPokemons)

        val chamander = PokemonArrays(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png",
            4,
            "Chamander",
            listOf( PokemonType("Fire") )
        )
        val pokemonsViews = listOf(chamander,chamander,chamander,chamander,chamander)

        
        val pokemonsApi = PokemonRepository.listPokemons()

        Log.d("POKEMON_API", pokemonsApi.toString())

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PokemonAdapter(pokemonsViews )

    }
}