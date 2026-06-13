package com.example.pokedex_mobile

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

enum class ElementsColors(val color: Color) {
    Acier(Color(0xFFB7B7CE)),
    Combat(Color(0xFFC22E28)),
    Dragon(Color(0xFF6F35FC)),
    Eau(Color(0xFF6390F0)),
    Électrik(Color(0xFFF7D02C)),
    Fée(Color(0xFFD685AD)),
    Feu(Color(0xFFEE8130)),
    Glace(Color(0xFF96D9D6)),
    Insecte(Color(0xFFA6B91A)),
    Normal(Color(0xFFA8A77A)),
    Plante(Color(0xFF7AC74C)),
    Poison(Color(0xFFA33EA1)),
    Psy(Color(0xFFF95587)),
    Roche(Color(0xFFB6A136)),
    Sol(Color(0xFFE2BF65)),
    Spectre(Color(0xFF735797)),
    Ténèbres(Color(0xFF705746)),
    Vol(Color(0xFFA98FF3));

    val rgb: Int get() = color.toArgb()

    fun getLightTone(factor: Float = 0.7f): Int {
        val a = android.graphics.Color.alpha(rgb)
        val r = android.graphics.Color.red(rgb)
        val g = android.graphics.Color.green(rgb)
        val b = android.graphics.Color.blue(rgb)

        val newR = (r + (255 - r) * factor).toInt().coerceIn(0, 255)
        val newG = (g + (255 - g) * factor).toInt().coerceIn(0, 255)
        val newB = (b + (255 - b) * factor).toInt().coerceIn(0, 255)

        return android.graphics.Color.argb(a, newR, newG, newB)
    }


    fun getDarkTone(factor: Float = 0.4f): Int {
        val a = android.graphics.Color.alpha(rgb)
        val r = android.graphics.Color.red(rgb)
        val g = android.graphics.Color.green(rgb)
        val b = android.graphics.Color.blue(rgb)

        val newR = (r * (1 - factor)).toInt().coerceIn(0, 255)
        val newG = (g * (1 - factor)).toInt().coerceIn(0, 255)
        val newB = (b * (1 - factor)).toInt().coerceIn(0, 255)

        return android.graphics.Color.argb(a, newR, newG, newB)
    }
}