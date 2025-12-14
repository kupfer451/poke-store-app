package com.example.pokestore

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val btnBack = findViewById<ImageButton>(R.id.btnBackCart)
        btnBack.setOnClickListener { finish() }

        val btnConfirm = findViewById<Button>(R.id.btnConfirmOrder)
        btnConfirm.setOnClickListener {
            val cartIsEmpty = true

            if (cartIsEmpty) {
                val snackbar = Snackbar.make(it, "Error: No hay productos seleccionados", Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(getColor(R.color.error_red))
                snackbar.setTextColor(getColor(R.color.white))
                snackbar.show()
            } else {
            }
        }
    }
}
