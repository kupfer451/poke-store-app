package com.example.pokestore

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnProducts = findViewById<Button>(R.id.btnNavProducts)
        btnProducts.setOnClickListener {
            val intent = Intent(this, ProductsActivity::class.java)
            startActivity(intent)
        }

        val btnAbout = findViewById<Button>(R.id.btnNavAbout)
        btnAbout.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        val btnCart = findViewById<Button>(R.id.btnNavCart)
        btnCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        val btnLogin = findViewById<Button>(R.id.btnNavLogin)
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val btnRegister = findViewById<Button>(R.id.btnNavRegister)
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        setupFeaturedProducts()

        setupNewProducts()
    }

    private fun setupFeaturedProducts() {
        val featuredProducts = ProductData.allProducts.filter { it.id in 1..4 }

        val rvFeatured = findViewById<RecyclerView>(R.id.rvFeaturedProducts)
        rvFeatured.layoutManager = GridLayoutManager(this, 2)
        rvFeatured.adapter = ProductAdapter(featuredProducts)
    }

    private fun setupNewProducts() {
        val newProducts = ProductData.allProducts.filter { it.id in 5..8 }

        val rvNew = findViewById<RecyclerView>(R.id.rvNewProducts)
        rvNew.layoutManager = GridLayoutManager(this, 2)
        rvNew.adapter = ProductAdapter(newProducts)
    }
}
