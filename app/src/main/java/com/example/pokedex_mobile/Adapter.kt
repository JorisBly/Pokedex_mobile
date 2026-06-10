package com.example.pokedex_mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.Pokemon
import com.example.pokedex_mobile.databinding.ItemPokemonBinding

class Adapter(private val pokemonList: List<Pokemon>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

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
        }


    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    class ViewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root)
}