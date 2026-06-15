package com.example.pokedex_mobile

import android.R
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pokedex.Pokemon
import com.example.pokedex_mobile.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import kotlin.collections.take

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var pokemonList = mutableListOf<Pokemon>()
    private var filteredPokemons = mutableListOf<Pokemon>()

    private var elementFilter = "Tous"

    private var nameFilter = ""
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pokemonAdapter = PokemonAdapter(filteredPokemons)
        binding.recyclerview.adapter = pokemonAdapter

        startMainScreen()
    }

    private fun startMainScreen() {
            lifecycleScope.launch {
                try {
                    val pokemons = TyradexClient.api.getAll()
                    pokemonList.addAll(pokemons)
                    updateMainScreen()
                    styleAllButton()
                    generateTypeChips()
                    addListenerToSearchBar()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }

    private fun updateMainScreen(){
        filteredPokemons.clear()

        if (elementFilter == "Tous" && nameFilter == ""){
            filteredPokemons.addAll(filterPokemon().take(21))
            filteredPokemons.removeAt(0)
        }else{
            filteredPokemons.addAll(filterPokemon())
        }
        pokemonAdapter.notifyDataSetChanged()
    }

    private fun generateTypeChips() {
        for (elementType in ElementsColors.values()) {

            val chip = Chip(this, null, com.google.android.material.R.attr.chipStyle).apply {
                text = elementType.name
                isCheckable = true
                chipCornerRadius = dpToPx()
                chipStrokeWidth = 0f

                val backgroundStates = ColorStateList(
                    arrayOf(
                        intArrayOf(android.R.attr.state_checked),
                        intArrayOf(-android.R.attr.state_checked)
                    ),
                    intArrayOf(
                        Color.parseColor("#E3350D"),
                        elementType.getLightTone()
                    )
                )

                val textStates = ColorStateList(
                    arrayOf(
                        intArrayOf(android.R.attr.state_checked),
                        intArrayOf(-android.R.attr.state_checked)
                    ),
                    intArrayOf(
                        Color.WHITE,
                        elementType.getDarkTone()
                    )
                )

                chipBackgroundColor = backgroundStates
                setTextColor(textStates)
            }

            binding.chipGroupTypes.addView(chip)

            chip.setOnClickListener {
                elementFilter = chip.text.toString()
                updateMainScreen()
            }
        }
    }

    private fun styleAllButton() {
        val allBackgroundStates = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf(-android.R.attr.state_checked)
            ),
            intArrayOf(
                Color.parseColor("#E3350D"),
                Color.parseColor("#EAEAEA")
            )
        )

        val allTextStates = ColorStateList(
            arrayOf(
                intArrayOf(R.attr.state_checked),
                intArrayOf(-R.attr.state_checked)
            ),
            intArrayOf(Color.WHITE, Color.parseColor("#666666"))
        )

        binding.chipAll.apply {
            chipBackgroundColor = allBackgroundStates
            setTextColor(allTextStates)
            chipStrokeWidth = 0f
        }

        binding.chipAll.setOnClickListener {
            elementFilter = binding.chipAll.text.toString()
            updateMainScreen()
        }
    }

    private fun dpToPx(dp: Int = 12): Float {
        return dp * resources.displayMetrics.density
    }

    private fun addListenerToSearchBar() {
        binding.etSearch.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    nameFilter = s.toString()

                    updateMainScreen()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                }
            }
        )
    }

    private fun filterPokemon(): List<Pokemon> {
        var result = pokemonList.filter { pokemon ->

            val matchedType = if (elementFilter == "Tous"){
                true
            }else {
                pokemon.types?.any {type -> type.name.equals(elementFilter)}
            }

            val containName = pokemon.name.fr.lowercase().contains(nameFilter.lowercase())
            matchedType == true && containName
        }

        return result

    }

}