package com.example.pokestore

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        val rvProducts = findViewById<RecyclerView>(R.id.rvAllProducts)
        rvProducts.layoutManager = LinearLayoutManager(this)
        
        rvProducts.adapter = ProductAdapter(ProductData.allProducts)
    }
}
