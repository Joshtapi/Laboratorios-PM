package com.miempresa.sesion10

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener los contenedores de fragmento
        val contenedorFragmento1 = findViewById<FrameLayout>(R.id.contenedorFragmento1)
        val contenedorFragmento2 = findViewById<FrameLayout>(R.id.contenedorFragmento2)

        // Cargar el primer fragmento en el contenedor
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(contenedorFragmento1.id, Fragmento1())
        fragmentTransaction.commit()
    }
}
