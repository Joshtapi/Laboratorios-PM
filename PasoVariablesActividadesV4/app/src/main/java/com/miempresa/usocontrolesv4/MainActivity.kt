package com.miempresa.usocontrolesv4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
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
            "Cerrar"
        )
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,android.R.id.text1,
            values
        )

        lista.adapter = adapter

        lista.onItemClickListener =
            OnItemClickListener { parent,view,position,id ->
                val itemPosition = position
                val itemValue = lista.getItemAtPosition(position) as String
                val seleccionados = txtseleccionados.text.toString()
                Toast.makeText(this,"Position: "+itemPosition+"Descripcion: "+itemValue,
                   Toast.LENGTH_LONG).show()
            }
    }
}