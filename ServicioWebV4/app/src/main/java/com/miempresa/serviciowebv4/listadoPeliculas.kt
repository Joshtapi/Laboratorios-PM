package com.miempresa.serviciowebv4


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class listadoPeliculas : AppCompatActivity() {
    private lateinit var btnPeliculas: Button
    private lateinit var btnUsuarios: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_peliculas)

        btnPeliculas = findViewById(R.id.btnPeliculas)
        btnUsuarios = findViewById(R.id.btnUsuarios)

        btnPeliculas.setOnClickListener {
            mostrarFragmentoPeliculas()
        }

        btnUsuarios.setOnClickListener {
            mostrarFragmentoUsuarios()
        }

    }

    private fun mostrarFragmentoPeliculas() {
        val fragmentPeliculas = FragmentPeliculas()

        mostrarFragmento(fragmentPeliculas)
    }

    private fun mostrarFragmentoUsuarios() {
        val fragmentUsuarios = FragmentUsuarios()

        mostrarFragmento(fragmentUsuarios)
    }

    private fun mostrarFragmento(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

}
