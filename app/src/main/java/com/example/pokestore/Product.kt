package com.example.pokestore

import java.io.Serializable

data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val imageResId: Int,
    val description: String = "Esta es una carta coleccionable de alta rareza. Perfecta para tu mazo o carpeta de colección. Estado: Near Mint (Casi nueva)."
) : Serializable
