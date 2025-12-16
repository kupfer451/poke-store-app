package com.example.pokestore

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokestore.network.RetrofitClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // 1. Declarar adaptadores como propiedades para actualizarlos luego
    private lateinit var featuredAdapter: ProductAdapter
    private lateinit var newProductsAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigationButtons()
        setupRecyclerViews() // 2. Configurar vistas vacías
        fetchHomeData()      // 3. Cargar datos de la API
    }

    private fun setupNavigationButtons() {
        val btnProducts = findViewById<Button>(R.id.btnNavProducts)
        btnProducts.setOnClickListener {
            startActivity(Intent(this, ProductsActivity::class.java))
        }

        val btnAbout = findViewById<Button>(R.id.btnNavAbout)
        btnAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        val btnCart = findViewById<Button>(R.id.btnNavCart)
        btnCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        val btnLogin = findViewById<Button>(R.id.btnNavLogin)
        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        val btnRegister = findViewById<Button>(R.id.btnNavRegister)
        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun setupRecyclerViews() {
        // Configurar RecyclerView de Destacados (vacío inicialmente)
        val rvFeatured = findViewById<RecyclerView>(R.id.rvFeaturedProducts)
        rvFeatured.layoutManager = GridLayoutManager(this, 2)
        // Importante: Asegúrate de que el adapter soporte listas vacías al inicio
        featuredAdapter = ProductAdapter(emptyList())
        rvFeatured.adapter = featuredAdapter

        // Configurar RecyclerView de Nuevos (vacío inicialmente)
        val rvNew = findViewById<RecyclerView>(R.id.rvNewProducts)
        rvNew.layoutManager = GridLayoutManager(this, 2)
        newProductsAdapter = ProductAdapter(emptyList())
        rvNew.adapter = newProductsAdapter
    }

    private fun fetchHomeData() {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getProducts()

                if (response.isSuccessful && response.body() != null) {
                    val allProducts = response.body()!!

                    // 4. Lógica de filtrado adaptada a la API:
                    // Tomamos los primeros 4 como "Destacados"
                    val featuredProducts = allProducts.take(4)

                    // Tomamos los siguientes 4 como "Nuevos" (o los que queden)
                    val newProducts = allProducts.drop(4).take(4)

                    // 5. Actualizar los adaptadores con los datos reales
                    featuredAdapter.updateData(featuredProducts)
                    newProductsAdapter.updateData(newProducts)
                } else {
                    Toast.makeText(this@MainActivity, "Error al cargar productos", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Sin conexión al servidor", Toast.LENGTH_SHORT).show()
            }
        }
    }
}