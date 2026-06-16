package com.example.pokedex_mobile

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pokedex.Pokemon
import com.example.pokedex_mobile.databinding.ActivityPokemonDetailsBinding
import kotlinx.coroutines.launch

class PokemonDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val pokemonId = intent.getIntExtra("POKEMON_ID", 1)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPokemonDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadPokemon(pokemonId.toString())

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun loadPokemon(pokemonId: String) {
        lifecycleScope.launch {
            try {
                val pokemon = TyradexClient.api.getPokemon(pokemonId)
                val firstType = pokemon?.types?.firstOrNull()?.name ?: "Normal"
                val secondType = if (pokemon?.types?.size == 2) pokemon.types[1].name else null
                val elementColor = ElementsColors.valueOf(firstType)

                updateTypes(firstType, elementColor, secondType)

                updatePokemonInfo(pokemon)


                updateProgressList(pokemon, elementColor)

                setBackground(elementColor)


                binding.tvDetailName.setTextColor(elementColor.getDarkTone(factor = 0.5f))
                binding.tvDetailCategory.setTextColor(elementColor.getDarkTone(factor = 0.4f))

                com.bumptech.glide.Glide.with(this@PokemonDetailsActivity)
                    .load(pokemon?.sprites?.regular)
                    .into(binding.ivDetailSprite)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private fun updateTypes(
        firstType: String,
        elementColor: ElementsColors,
        secondType: String?
    ) {
        binding.tvDetailType1.text = firstType
        binding.tvDetailType1.chipBackgroundColor = ColorStateList.valueOf(elementColor.rgb)

        if (secondType != null) {
            val secondElementColor = ElementsColors.valueOf(secondType)
            binding.tvDetailType2.visibility = View.VISIBLE
            binding.tvDetailType2.text = secondType
            binding.tvDetailType2.chipBackgroundColor =
                ColorStateList.valueOf(secondElementColor.rgb)
        } else {
            binding.tvDetailType2.visibility = View.GONE
        }
    }

    private fun updatePokemonInfo(pokemon: Pokemon?) {
        binding.tvDetailName.text = pokemon?.name?.fr
        binding.tvDetailCategory.text = pokemon?.category

        binding.tvDetailWeight.text = pokemon?.weight ?: ""
        binding.tvDetailHeight.text = pokemon?.height ?: ""
        binding.tvDetailGeneration.text = "Gén. ${pokemon?.generation ?: "Inconnu"}"
    }

    private fun setBackground(elementColor: ElementsColors) {
        binding.main.setBackgroundColor(elementColor.getLightTone(factor = 0.85f))
    }

    private fun updateProgressList(pokemon: Pokemon? ,elementColor: ElementsColors) {

        binding.pbHp.progress = pokemon?.stats?.hp ?: 0
        binding.tvValueHp.text = pokemon?.stats?.hp?.toString() ?: ""
        binding.pbAtk.progress = pokemon?.stats?.atk ?: 0
        binding.tvValueAtk.text = pokemon?.stats?.atk?.toString() ?: ""
        binding.pbDef.progress = pokemon?.stats?.def ?: 0
        binding.tvValueDef.text = pokemon?.stats?.def?.toString() ?: ""
        binding.pbSpeAtk.progress = pokemon?.stats?.speAtk ?: 0
        binding.tvValueSpeAtk.text = pokemon?.stats?.speAtk?.toString() ?: ""

        binding.pbHp.progressTintList = ColorStateList.valueOf(elementColor.rgb)
        binding.pbAtk.progressTintList = ColorStateList.valueOf(elementColor.rgb)
        binding.pbDef.progressTintList = ColorStateList.valueOf(elementColor.rgb)
        binding.pbSpeAtk.progressTintList = ColorStateList.valueOf(elementColor.rgb)

        binding.pbHp.progressBackgroundTintList = ColorStateList.valueOf(elementColor.getLightTone(factor = 0.5f))
        binding.pbAtk.progressBackgroundTintList = ColorStateList.valueOf(elementColor.getLightTone(factor = 0.5f))
        binding.pbDef.progressBackgroundTintList = ColorStateList.valueOf(elementColor.getLightTone(factor = 0.5f))
        binding.pbSpeAtk.progressBackgroundTintList = ColorStateList.valueOf(elementColor.getLightTone(factor = 0.5f))
    }
}