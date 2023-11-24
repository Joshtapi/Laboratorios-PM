package com.miempresa.googlemapv4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val values = arrayOf(
            "Plaza de Armas , Arequipa",
            "Machu Pichu , Cuzco",
            "Valle del colca , Arequipa",
            "Lineas de nazca , Ica",
            "Punta sal , Tumbes",
            "Amazonas"
        )
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, android.R.id.text1,
            values
        )
        val btnAgregarDestino = findViewById<Button>(R.id.btnAgregarDestino)
        btnAgregarDestino.setOnClickListener {
            val intent = Intent(this, AgregarDestinoActivity::class.java)
            startActivity(intent)
        }


        lstDestinos.adapter = adapter
        lstDestinos.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val itemValue =
                    lstDestinos.getItemAtPosition(i) as String
                val intent = Intent(this@MainActivity, MapsActivity::class.java)
                intent.putExtra("destino", itemValue.toLowerCase())
                startActivity(intent)
            }
    }
}
