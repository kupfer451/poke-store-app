package com.example.pokestore

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnBack = findViewById<ImageButton>(R.id.btnBackRegister)
        btnBack.setOnClickListener { finish() }

        val btnRegister = findViewById<Button>(R.id.btnRegisterAction)
        val etName = findViewById<EditText>(R.id.etRegisterName)
        val etEmail = findViewById<EditText>(R.id.etRegisterEmail)
        val etRut = findViewById<EditText>(R.id.etRegisterRut)
        val etPassword = findViewById<EditText>(R.id.etRegisterPassword)

        btnRegister.setOnClickListener {
            // Ocultar teclado
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val rut = etRut.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || rut.isEmpty() || password.isEmpty()) {
                // Usar Snackbar en lugar de Toast para mostrar error personalizado
                val snackbar = Snackbar.make(it, "Por favor, completa todos los campos", Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(getColor(R.color.error_red))
                snackbar.setTextColor(getColor(R.color.white))
                snackbar.show()
            } else {
                // Lógica de registro exitoso
                Toast.makeText(this, "¡Cuenta creada para $name!", Toast.LENGTH_SHORT).show()
                finish() // Vuelve a la pantalla de inicio
            }
        }
    }
}
