package com.example.pokestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // --- Configurar Productos Destacados ---
        setupFeaturedProducts()

        // --- Configurar Productos Nuevos ---
        setupNewProducts()
    }

    private fun setupFeaturedProducts() {
        val featuredProducts = listOf(
            Product(1, "Charizard Base", "$150.00", android.R.drawable.ic_menu_gallery),
            Product(2, "Pikachu VMAX", "$45.00", android.R.drawable.ic_menu_camera),
            Product(3, "Mewtwo EX", "$80.00", android.R.drawable.ic_menu_compass),
            Product(4, "Eevee Heroes", "$25.00", android.R.drawable.ic_menu_view)
        )

        val rvFeatured = findViewById<RecyclerView>(R.id.rvFeaturedProducts)
        rvFeatured.layoutManager = GridLayoutManager(this, 2) // 2 Columnas
        rvFeatured.adapter = ProductAdapter(featuredProducts)
    }

    private fun setupNewProducts() {
        val newProducts = listOf(
            Product(5, "Umbreon Star", "$300.00", android.R.drawable.btn_star_big_on),
            Product(6, "Rayquaza Gold", "$450.00", android.R.drawable.star_on),
            Product(7, "Gardevoir EX", "$75.00", android.R.drawable.ic_dialog_alert),
            Product(8, "Lugia V", "$200.00", android.R.drawable.ic_dialog_map)
        )

        val rvNew = findViewById<RecyclerView>(R.id.rvNewProducts)
        rvNew.layoutManager = GridLayoutManager(this, 2) // 2 Columnas
        rvNew.adapter = ProductAdapter(newProducts)
    }
}
