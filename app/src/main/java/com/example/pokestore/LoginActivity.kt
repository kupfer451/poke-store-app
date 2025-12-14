package com.example.pokestore

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnBack = findViewById<ImageButton>(R.id.btnBackLogin)
        btnBack.setOnClickListener { finish() }

        val btnLogin = findViewById<Button>(R.id.btnLoginAction)
        val etEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etPassword = findViewById<EditText>(R.id.etLoginPassword)

        btnLogin.setOnClickListener {
            // Ocultar teclado al presionar el botón
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Variable para controlar si hay error y mostrar Snackbar
            var hasError = false
            var errorMessage = ""

            if (email.isEmpty()) {
                etEmail.error = "Ingresa tu correo electrónico"
                hasError = true
                errorMessage = "Por favor completa todos los campos"
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Ingresa un correo válido"
                hasError = true
                errorMessage = "Formato de correo inválido"
            } else if (password.isEmpty()) {
                etPassword.error = "Ingresa tu contraseña"
                hasError = true
                errorMessage = "Por favor completa todos los campos"
            } else if (password.length < 6) {
                etPassword.error = "La contraseña debe tener al menos 6 caracteres"
                hasError = true
                errorMessage = "Contraseña demasiado corta"
            }

            if (hasError) {
                // Mostrar Snackbar rojo igual que en Registro
                val snackbar = Snackbar.make(it, errorMessage, Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(getColor(R.color.error_red))
                snackbar.setTextColor(getColor(R.color.white))
                snackbar.show()
            } else {
                // Si pasa todas las validaciones
                Toast.makeText(this, "¡Bienvenido, $email!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
