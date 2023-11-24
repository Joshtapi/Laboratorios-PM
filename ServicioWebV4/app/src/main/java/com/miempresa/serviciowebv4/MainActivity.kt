package com.miempresa.serviciowebv4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.content.Intent
import android.widget.Toast
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        btnLoguear.setOnClickListener {
            val usuario = txtUsuario.text.toString()
            val clave = txtClave.text.toString()
            val queue = Volley.newRequestQueue(this)
            val url = getString(R.string.urlAPI) + "/usuarios?"

            val stringRequest = JsonArrayRequest(url,
                { response ->
                    try {
                        val valor = response.getJSONObject(0)
                        /*Toast.makeText(
                            applicationContext,
                            "Validación de usuario: " + valor.getString("usuario") +
                                    " con clave: " + valor.getString("clave") + " correcta",
                            Toast.LENGTH_LONG
                        ).show()*/
                        val llamaractividad =
                            Intent(applicationContext, listadoPeliculas::class.java)
                        startActivity(llamaractividad)
                        finish()
                    } catch (e: JSONException) {
                        Toast.makeText(
                            applicationContext,
                            "Error en las credenciales proporcionadas",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                },
                { error ->
                    Toast.makeText(
                        applicationContext,
                        "Error al conectar con el servidor: " + error.message,
                        Toast.LENGTH_LONG
                    ).show()
                })

            queue.add(stringRequest)
        }

        btnRegistrar.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        btnSalir.setOnClickListener {
            finish()
        }
    }
}
