package com.example.pokestore

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private var currentProductIndex = 0
    private lateinit var productList: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val btnBack = findViewById<ImageButton>(R.id.btnBackDetail)
        btnBack.setOnClickListener { finish() }

        @Suppress("DEPRECATION")
        val initialProduct = intent.getSerializableExtra("PRODUCT_EXTRA") as? Product

        productList = ProductData.allProducts

        if (initialProduct != null) {
            currentProductIndex = productList.indexOfFirst { it.id == initialProduct.id }
            if (currentProductIndex == -1) currentProductIndex = 0
        }

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

        updateUI()
    }

    private fun updateUI() {
        val product = productList[currentProductIndex]

        val img = findViewById<ImageView>(R.id.imgDetail)
        val txtName = findViewById<TextView>(R.id.txtDetailName)
        val txtPrice = findViewById<TextView>(R.id.txtDetailPrice)
        val txtDesc = findViewById<TextView>(R.id.txtDetailDescription)
        val btnAdd = findViewById<Button>(R.id.btnDetailAddCart)
        val btnPrev = findViewById<ImageButton>(R.id.btnPrevProduct)
        val btnNext = findViewById<ImageButton>(R.id.btnNextProduct)

        img.setImageResource(product.imageResId)
        txtName.text = product.name
        txtPrice.text = product.price
        txtDesc.text = product.description

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
