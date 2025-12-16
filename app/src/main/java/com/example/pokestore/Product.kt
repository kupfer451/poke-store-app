package com.example.pokestore

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    val id: String, // Cambiado de Int a String (UUID)

    @SerializedName("product_name") // Mapea el JSON snake_case a camelCase
    val name: String,

    val price: Double, // La API devuelve BigDecimal, lo manejamos como Double

    @SerializedName("image_url")
    val imageUrl: String?, // Ahora es una URL, no un recurso R.drawable

    val description: String,

    val category: String?
) : Serializable