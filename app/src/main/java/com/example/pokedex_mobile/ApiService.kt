package com.example.pokedex_mobile

import com.example.pokedex.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("api/v1/pokemon")
    suspend fun getAll(): List<Pokemon>

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: String): Pokemon?
}



