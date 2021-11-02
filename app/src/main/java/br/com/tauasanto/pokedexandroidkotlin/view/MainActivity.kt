package br.com.tauasanto.pokedexandroidkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.tauasanto.pokedexandroidkotlin.R
import br.com.tauasanto.pokedexandroidkotlin.api.PokemonRepository
import br.com.tauasanto.pokedexandroidkotlin.domain.PokemonArrays
import br.com.tauasanto.pokedexandroidkotlin.viewmodel.PokemonViewModel
import br.com.tauasanto.pokedexandroidkotlin.viewmodel.PokemonViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    private val viewModel by lazy {
        ViewModelProvider( this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rvPokemons)

       viewModel.pokemons.observe(this, Observer {
           loadRecyclerView(it)
       })
    }
    private fun loadRecyclerView(pokemons : List<PokemonArrays?>) {

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}