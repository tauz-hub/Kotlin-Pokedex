package br.com.tauasanto.pokedexandroidkotlin.api

import android.util.Log
import br.com.tauasanto.pokedexandroidkotlin.api.model.PokemonApiResult
import br.com.tauasanto.pokedexandroidkotlin.api.model.PokemonsApiResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {
    //https://pokeapi.co/api/v2/pokemon/?limit=151

    private val service : PokemonService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

      service = retrofit.create(PokemonService::class.java)
    }
    fun listPokemons (limit: Int = 111): PokemonsApiResult?{
        val call = service.listPokemons(limit)


        return call.execute().body()
//        call.enqueue(object : Callback<PokemonsApiResult> {
//            override fun onResponse(
//                call: Call<PokemonsApiResult>,
//                response: Response<PokemonsApiResult>
//            ) {
//                if(response.isSuccessful){
//                    val body = response.body()
//
//                    body?.results?.let {
//                        Log.d("POKEMON_API", it[0].name)
//                    }
//                }
//                Log.e("POKEMONS_API", "Pokemons carregados")
//            }
//
//            override fun onFailure(call: Call<PokemonsApiResult>, t: Throwable) {
//                Log.e("POKEMON_API", "erro ao carregar pokemons", t)
//            }
//        })
    }
}