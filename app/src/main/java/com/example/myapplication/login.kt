package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        btnIniciarSesion.setOnClickListener {
            val intent = Intent(this, desc::class.java)
            startActivity(intent)
        }
        val btnRetroceder = findViewById<Button>(R.id.btnRetroceder)
        btnRetroceder.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}