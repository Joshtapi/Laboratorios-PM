package com.miempresa.juegos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_cursos.*
import kotlinx.android.synthetic.main.activity_main.*

class cursos : AppCompatActivity() {
    var datosactividad01:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cursos)

        val values = arrayOf(
            "Programación en Java",
            "Diseño gráfico",
            "Marketing Digital",
            "Inglés",
            "Fotografía",
            "Cocina",
            "Costura"
        )
        val adapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, android.R.id.text1,
            values
        )

        listadecursos.adapter = adapter
        listadecursos.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val itemPosition = position
                val itemValue = listadecursos.getItemAtPosition(position) as String
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("parametro", itemValue)
                intent.putExtra("datosactividad01", datosactividad01)
                this.finish()
                startActivity(intent)
            }
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            datosactividad01 = bundle.getString("seleccionados").toString()
        }
    }
}
