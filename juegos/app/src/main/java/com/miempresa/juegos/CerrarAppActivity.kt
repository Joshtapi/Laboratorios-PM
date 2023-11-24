package com.miempresa.juegos

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cerrar_app.*

class CerrarAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cerrar_app)

        // Mostrar un diálogo de confirmación al usuario
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Estás seguro de querer cerrar la aplicación?")
        builder.setPositiveButton("Sí") { dialogInterface: DialogInterface, i: Int ->
            // Finalizar la actividad principal y cerrar la aplicación
            finishAffinity()
        }
        builder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
            // Volver a la actividad principal
            finish()
        }
        builder.setCancelable(false)
        builder.show()

        // Configurar el botón de "Volver" para que cierre la actividad actual
        boton_confirmar.setOnClickListener {
            finishAffinity() // cierra todas las actividades abiertas
            System.exit(0) // cierra el proceso de la aplicación
        }
        boton_cancelar.setOnClickListener {
            onBackPressed() // vuelve a la actividad anterior
        }
    }
}
