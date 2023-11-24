package com.miempresa.serviciowebv4

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class RegistroActivity : AppCompatActivity() {
    private lateinit var etNombreUsuario: EditText
    private lateinit var etClave: EditText
    private lateinit var etEstado: EditText
    private lateinit var btnRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Inicializar los elementos de la interfaz de usuario
        etNombreUsuario = findViewById(R.id.etNombreUsuario)
        etClave = findViewById(R.id.etClave)
        etEstado = findViewById(R.id.etEstado)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        // Agregar la funcionalidad al botón de registro
        btnRegistrar.setOnClickListener {
            val nombreUsuario = etNombreUsuario.text.toString()
            val clave = etClave.text.toString()
            val estado = etEstado.text.toString()

            if (nombreUsuario.isNotEmpty() && clave.isNotEmpty() && estado.isNotEmpty()) {
                // Todos los campos están completos, realizar el registro
                val registroExitoso = verificarRegistro(nombreUsuario, clave, estado)

                if (registroExitoso) {
                    Toast.makeText(
                        applicationContext,
                        "Registro exitoso para el usuario: $nombreUsuario",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Redirige al usuario a MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Opcional: finaliza la actividad actual para que no se pueda volver atrás
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Error en el registro",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                // Alguno de los campos está vacío, mostrar mensaje de error
                Toast.makeText(
                    applicationContext,
                    "Completa todos los datos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Obtener referencia al botón "btnVolver"
        val btnVolver: Button = findViewById(R.id.btnVolver)

        // Agregar la funcionalidad al botón "btnVolver"
        btnVolver.setOnClickListener {
            // Mostrar el diálogo de alerta
            showConfirmationDialog()
        }
    }

    private fun showConfirmationDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Confirmación")
        alertDialogBuilder.setMessage("¿Estás seguro de no registrar más usuarios?")
        alertDialogBuilder.setPositiveButton("Volver") { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss() // Cierra el diálogo
            // No se realiza ninguna acción adicional, se mantiene en la actividad actual (RegistroActivity)
        }
        alertDialogBuilder.setNegativeButton("Ir a inicio") { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss() // Cierra el diálogo
            // Redirige al usuario a MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Opcional: finaliza la actividad actual para que no se pueda volver atrás
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun verificarRegistro(nombreUsuario: String, clave: String, estado: String): Boolean {
        val client = OkHttpClient()

        // Construye los datos que enviarás al servidor
        val formBody = FormBody.Builder()
            .add("usuario", nombreUsuario)
            .add("clave", clave)
            .add("estado", estado)
            .build()

        // Construye la solicitud POST al servidor
        val request = Request.Builder()
            .url(getString(R.string.urlAPI) + "/usuarios") // Concatena la parte "/usuarios" a la URL del servidor desde los recursos
            .post(formBody) // Agrega los datos al cuerpo de la solicitud
            .build()

        // Envía la solicitud al servidor y obtiene la respuesta
        try {
            val response = client.newCall(request).execute()

            // Verifica el código de respuesta del servidor
            return response.isSuccessful
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return false
    }

}
