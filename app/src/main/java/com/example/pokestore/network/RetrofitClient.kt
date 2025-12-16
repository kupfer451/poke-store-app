package com.example.pokestore.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Si usas el emulador de Android, 10.0.2.2 apunta al localhost de tu PC.
    // Si usas un dispositivo físico, usa la IP local de tu PC (ej: 192.168.1.X)
    private const val BASE_URL = "https://ykc5im-ip-201-187-237-56.tunnelmole.net/"

    val instance: PokeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }
}