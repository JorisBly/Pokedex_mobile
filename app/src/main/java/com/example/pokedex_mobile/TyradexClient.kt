package com.example.pokedex_mobile

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue
import kotlin.jvm.java


object RetrofitClient {
    private const val BASE_URL = "https://tyradex.app/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}


object TyradexClient {
    val api: ApiService by lazy {
        RetrofitClient.retrofit.create(ApiService::class.java)
    }
}
