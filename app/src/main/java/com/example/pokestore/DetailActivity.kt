package com.example.pokestore

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.pokestore.network.RetrofitClient
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private var currentProductIndex = 0
    private var productList: List<Product> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val btnBack = findViewById<ImageButton>(R.id.btnBackDetail)
        btnBack.setOnClickListener { finish() }

        // 1. Recibimos el producto inicial pasado desde la actividad anterior
        @Suppress("DEPRECATION")
        val initialProduct = intent.getSerializableExtra("PRODUCT_EXTRA") as? Product

        // Si tenemos un producto, lo mostramos inmediatamente y lo ponemos en una lista temporal
        if (initialProduct != null) {
            productList = listOf(initialProduct)
            updateUI()
        }

        // 2. Cargamos la lista completa desde la API para que funcionen los botones "Anterior" y "Siguiente"
        // Pasamos el ID del producto actual para encontrar su posición cuando llegue la lista
        fetchProducts(initialProduct?.id)

        val btnPrev = findViewById<ImageButton>(R.id.btnPrevProduct)
        val btnNext = findViewById<ImageButton>(R.id.btnNextProduct)

        btnPrev.setOnClickListener {
            if (currentProductIndex > 0) {
                currentProductIndex--
                updateUI()
            }
        }

        btnNext.setOnClickListener {
            if (currentProductIndex < productList.size - 1) {
                currentProductIndex++
                updateUI()
            }
        }
    }

    private fun fetchProducts(currentId: String?) {
        lifecycleScope.launch {
            try {
                // Llamada a la API
                val response = RetrofitClient.instance.getProducts()

                if (response.isSuccessful && response.body() != null) {
                    productList = response.body()!!

                    // 3. Buscar el índice del producto que estamos viendo actualmente en la nueva lista
                    if (currentId != null) {
                        val index = productList.indexOfFirst { it.id == currentId }
                        if (index != -1) {
                            currentProductIndex = index
                        }
                    }

                    // Actualizar UI para habilitar/deshabilitar botones de navegación correctamente
                    updateUI()
                }
            } catch (e: Exception) {
                Toast.makeText(this@DetailActivity, "No se pudo cargar la lista completa", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUI() {
        if (productList.isEmpty()) return

        val product = productList[currentProductIndex]

        val img = findViewById<ImageView>(R.id.imgDetail)
        val txtName = findViewById<TextView>(R.id.txtDetailName)
        val txtPrice = findViewById<TextView>(R.id.txtDetailPrice)
        val txtDesc = findViewById<TextView>(R.id.txtDetailDescription)
        val btnAdd = findViewById<Button>(R.id.btnDetailAddCart)
        val btnPrev = findViewById<ImageButton>(R.id.btnPrevProduct)
        val btnNext = findViewById<ImageButton>(R.id.btnNextProduct)

        // 4. Usar Coil para cargar la imagen (URL)
        img.load(product.imageUrl) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_gallery)
            error(android.R.drawable.stat_notify_error)
        }

        txtName.text = product.name
        // Formatear precio (ahora es Double/BigDecimal en lugar de String directo)
        txtPrice.text = "$ ${product.price.toInt()}"
        txtDesc.text = product.description

        // 5. Lógica de botones Anterior/Siguiente
        btnPrev.isEnabled = currentProductIndex > 0
        btnPrev.alpha = if (btnPrev.isEnabled) 1.0f else 0.3f

        btnNext.isEnabled = currentProductIndex < productList.size - 1
        btnNext.alpha = if (btnNext.isEnabled) 1.0f else 0.3f

        btnAdd.setOnClickListener {
            val snackbar = Snackbar.make(it, "Producto Agregado: ${product.name}", Snackbar.LENGTH_SHORT)
            snackbar.setBackgroundTint(getColor(R.color.success_green))
            snackbar.setTextColor(getColor(R.color.white))
            snackbar.show()
        }
    }
}