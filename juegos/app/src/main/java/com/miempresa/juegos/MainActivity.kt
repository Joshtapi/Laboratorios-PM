package com.miempresa.juegos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtseleccionados.isFocusable = false
        val values = arrayOf(
            "Juegos",
            "Aplicaciones",
            "Cursos",
            "Profesores",
            "Cerrar aplicación"
        )
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, android.R.id.text1,
            values
        )

        lista.adapter = adapter

        lista.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val itemPosition = position
                val itemValue = lista.getItemAtPosition(position) as String
                val seleccionados = txtseleccionados.text.toString()
                if (itemValue == "Juegos") {
                    val intent = Intent(this, juegos::class.java)
                    intent.putExtra("seleccionados",seleccionados)
                    startActivity(intent)
                } else if (itemValue == "Aplicaciones") {
                    val intent = Intent(this, aplicaciones::class.java)
                    intent.putExtra("seleccionados",seleccionados)
                    startActivity(intent)
                } else if (itemValue == "Cursos") {
                    val intent = Intent(this, cursos::class.java)
                    intent.putExtra("seleccionados",seleccionados)
                    startActivity(intent)
                } else if (itemValue == "Profesores") {
                    val intent = Intent(this, profesores::class.java)
                    intent.putExtra("seleccionados", seleccionados)
                    startActivity(intent)
                } else if (itemValue == "Cerrar aplicación") {
                     val intent = Intent(this, CerrarAppActivity::class.java)
                     startActivity(intent)
                } else if (itemValue == "Cerrar") {
                    finish()
                }
            }

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val recibidos = bundle.getString("parametro").toString()
            val datosactividad01 = bundle.getString("datosactividad01").toString()
            txtseleccionados.setText(datosactividad01 + recibidos + "\n")
        }
    }
}
