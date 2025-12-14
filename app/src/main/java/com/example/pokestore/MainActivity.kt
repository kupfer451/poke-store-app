package com.example.pokestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnEnterStore = findViewById<Button>(R.id.btnEnterStore)
        btnEnterStore.setOnClickListener {
            Toast.makeText(this, "Próximamente: Catálogo de cartas", Toast.LENGTH_SHORT).show()
        }
    }
}
