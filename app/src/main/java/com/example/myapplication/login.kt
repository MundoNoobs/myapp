package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.myapplication.databinding.ActivityLoginBinding

class login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicialización de la instancia de seguridad (cifrado TLS por defecto)
        auth = FirebaseAuth.getInstance()

        // Navegación explícita a la pantalla principal
        binding.btnRetroceder.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Destruye la actividad actual por seguridad y rendimiento
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etCorreo.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                iniciarSesionSegura(email, password)
            } else {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Control de acceso: evaluación de token de sesión activo
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Descomentar para salto automático si ya está logueado
            // startActivity(Intent(this, control::class.java))
            // finish()
        }
    }

    private fun iniciarSesionSegura(email: String, contrasena: String) {
        // Tránsito de credenciales encriptadas gestionado por Google Play Services
        auth.signInWithEmailAndPassword(email, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Autenticación exitosa", Toast.LENGTH_SHORT).show()
                    // Aquí puedes añadir el Intent para ir a control.kt tras el éxito
                } else {
                    // Respuesta genérica para mitigar intentos de enumeración de cuentas
                    Toast.makeText(this, "Error de autenticación.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
