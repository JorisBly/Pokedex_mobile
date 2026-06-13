package com.example.pokedex_mobile

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.Pokemon
import com.example.pokedex_mobile.databinding.ItemPokemonBinding

class PokemonAdapter(private val pokemonList: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val pokemon = pokemonList[position]
            with(holder){
                Glide.with(binding.ivPokemonSprite.context)
                    .load(pokemon.sprites.regular)
                    .into(binding.ivPokemonSprite)
                binding.tvPokemonId.text = pokemon.pokedexId.toString()
                binding.tvPokemonName.text = pokemon.name.fr
                pokemon.types?.let {
                    for ((index, value) in it.withIndex()) {
                        var bindedType = binding.tvType1
                        if(index > 0){
                            bindedType = binding.tvType2
                            }
                        bindedType.text = value.name
                        bindedType.setBackgroundColor(
                            ElementsColors.valueOf(value.name).rgb
                        )
                    }
            }
                binding.root.setOnClickListener {
                    val intent = Intent(it.context, PokemonDetailsActivity::class.java)
                    intent.putExtra("POKEMON_ID", pokemon.pokedexId)
                    it.context.startActivity(intent)
                }

        }


    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    class ViewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root)
}