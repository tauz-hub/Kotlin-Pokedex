package br.com.tauasanto.pokedexandroidkotlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.tauasanto.pokedexandroidkotlin.R
import br.com.tauasanto.pokedexandroidkotlin.domain.PokemonArrays
import com.bumptech.glide.Glide

class PokemonAdapter(
    private val pokemonsList: List<PokemonArrays?>

) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonsList[position]

        holder.bindView(pokemon)
    }

    override fun getItemCount() = pokemonsList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(pokemon: PokemonArrays?) = with(itemView) {
            val ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
            val tvNumber = findViewById<TextView>(R.id.tvNumber)
            val tvName = findViewById<TextView>(R.id.tvName)
            val tvType1 = findViewById<TextView>(R.id.tvType1)
            val tvType2 = findViewById<TextView>(R.id.tvType2)

            pokemon?.let {
                Glide.with(itemView.context).load(it.imageUrl).into(ivPokemon)

                tvNumber.text = "N° ${pokemon.formattedNumber}"
                tvName.text = pokemon.formattedName
                tvType1.text = pokemon.types[0].name.capitalize()

                if (pokemon.types.size > 1) {
                    tvType2.visibility = View.VISIBLE
                    tvType2.text = pokemon.types[1].name.capitalize()
                } else {
                    tvType2.visibility = View.GONE
                }
            }
        }
    }
}