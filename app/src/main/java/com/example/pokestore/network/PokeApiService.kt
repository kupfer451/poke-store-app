package com.example.pokestore.network

import com.example.pokestore.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// Modelos para Login/Registro
data class LoginRequest(val email: String, val password: String)
data class RegisterRequest(val username: String, val email: String, val rut: String, val password: String)
data class AuthResponse(val success: Boolean, val message: String, val token: String?, val user: UserData?)
data class UserData(val id: String, val username: String, val email: String)

interface PokeApiService {
    @GET("api/products")
    suspend fun getProducts(): Response<List<Product>>

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>
}