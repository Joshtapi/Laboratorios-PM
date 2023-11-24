package com.miempresa.usofragmentosv4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalles_contacto.*


class DetallesContacto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_contacto)

        val parametros: Bundle? = this.intent.extras
        if (parametros != null ){
            val recibidos = parametros.getString("nombre").toString()
            lblUsuario.text = recibidos
        }
    }
}