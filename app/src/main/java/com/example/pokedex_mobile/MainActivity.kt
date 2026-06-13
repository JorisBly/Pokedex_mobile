package com.example.pokedex_mobile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pokedex.Pokemon
import com.example.pokedex_mobile.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var pokemonList = mutableListOf<Pokemon>()
    private lateinit var pokemonAdapter: PokemonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pokemonAdapter = PokemonAdapter(pokemonList)
        binding.recyclerview.adapter = pokemonAdapter
        updateMainScreen()
    }

    private fun updateMainScreen() {
            lifecycleScope.launch {
                try {
                    val pokemons = TyradexClient.api.getAll()
                    pokemonList.addAll(pokemons.take(21))
                    pokemonList.removeAt(0)
                    pokemonAdapter.notifyDataSetChanged()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }

}