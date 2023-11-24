package com.miempresa.googlemapv4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_destinos.*

class destinos : AppCompatActivity() {
    private var destino = ""
    private var latitud = ""
    private var longitud = ""
    private var info = ""
    private var foto = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinos)

        val recibidos = intent.extras
        if (recibidos != null) {
            destino = intent.getStringExtra("destino") ?: ""
            latitud = intent.getStringExtra("latitud") ?: ""
            longitud = intent.getStringExtra("longitud") ?: ""
            info = intent.getStringExtra("info") ?: ""
            foto = intent.getIntExtra("foto", 0)
        }

        lblDestino.text = destino
        lblCoordenadas.text = "Latitud: $latitud, Longitud: $longitud"
        lblInfo.text = info
        imgFoto.setImageResource(foto)

        btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
