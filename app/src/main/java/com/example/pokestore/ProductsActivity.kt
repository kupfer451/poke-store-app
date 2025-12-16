package com.example.pokestore

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokestore.network.RetrofitClient
import kotlinx.coroutines.launch

class ProductsActivity : AppCompatActivity() {
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        // Configurar RecyclerView
        val rvProducts = findViewById<RecyclerView>(R.id.rvAllProducts)
        rvProducts.layoutManager = LinearLayoutManager(this)
        adapter = ProductAdapter(emptyList()) // Inicia vacío
        rvProducts.adapter = adapter

        // Llamar a la API
        fetchProducts()
    }

    private fun fetchProducts() {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getProducts()
                if (response.isSuccessful && response.body() != null) {
                    val products = response.body()!!
                    adapter.updateData(products)
                } else {
                    Toast.makeText(this@ProductsActivity, "Error al obtener productos", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@ProductsActivity, "Error de conexión: ${e.message}", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }
    }
}