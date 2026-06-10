package com.example.pokedex_mobile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex.Pokemon
import com.example.pokedex_mobile.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var pokemonList = mutableListOf<Pokemon>()
    private lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = Adapter(pokemonList)
        binding.recyclerview.adapter = adapter
        updateMainScreen()
    }

    private fun updateMainScreen() {
            lifecycleScope.launch {
                try {
                    val pokemons = TyradexClient.api.getAll()
                    pokemonList.addAll(pokemons)
                    pokemonList.removeAt(0)
                    adapter.notifyDataSetChanged()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }
//    private fun displayPokemon(id: Int) {
//
//        lifecycleScope.launch {
//            try {
//                val pokemon = ApiClient.api.getAll()
//                Glide.with(this@MainActivity)
//                    .load(pokemon.sprites.regular)
//                    .into(binding.imgSprite)
//                binding.idPokemon.text = "Num : " + id.toString()
//                binding.pokemonName.text = "Num : " + id + " " + pokemon.name.fr
//                binding.generation.text = "Gén. " + (pokemon.generation)
//                binding.category.text = pokemon.category.ifBlank { "Inconnue" }
//
//                binding.hp.text = "HP: " + pokemon.stats?.hp.toString()
//                binding.atk.text = "ATK: " + pokemon.stats?.atk.toString()
//                binding.def.text = "DEF: " + pokemon.stats?.def.toString()
//            } catch (e: Exception) {
//                binding.pokemonName.text = "Erreur de chargement"
//                e.printStackTrace()
//            }
//        }
//    }

}