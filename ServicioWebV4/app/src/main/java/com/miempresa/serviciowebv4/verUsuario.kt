package com.miempresa.serviciowebv4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_ver_usuario.*
import org.json.JSONObject
import java.util.HashMap

class verUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_usuario)

        val estados = arrayOf("A", "X")
        cmbEstados.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, estados)

        btnGuardar.setOnClickListener {
            agregarUsuario()
        }

        btnEliminar.setOnClickListener {
            eliminarUsuario()
        }

        btnEditar.setOnClickListener {
            editarUsuario()
        }

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            txtId.setText(bundle.getString("id").toString())
            txtUsuarios.setText(bundle.getString("nombre").toString())
            txtClaves.setText(bundle.getString("clave").toString())
            cmbEstados.setSelection(getEstadoIndex(bundle.getString("estado").toString()))

            btnGuardar.isEnabled = false
            btnEliminar.isEnabled = true
            btnEditar.isEnabled = true
        } else {
            btnGuardar.isEnabled = true
            btnEliminar.isEnabled = false
            btnEditar.isEnabled = false
        }
    }

    private fun agregarUsuario() {
        // Obtener los valores de los campos de entrada
        val id = txtId.text.toString()
        val nombre = txtUsuarios.text.toString()
        val clave = txtClaves.text.toString()
        val estado = cmbEstados.selectedItem.toString()

        // Crear el objeto JSON con los campos en el orden deseado
        val jsonBody = JSONObject()
        jsonBody.put("id", id)
        jsonBody.put("usuario", nombre)
        jsonBody.put("clave", clave)
        jsonBody.put("estado", estado)

        // Crear la cola de solicitudes de Volley
        val queue = Volley.newRequestQueue(this)
        val url = getString(R.string.urlAPI) + "/usuarios"
        val postRequest: StringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response -> // respuesta
                Toast.makeText(
                    applicationContext,
                    "Usuario agregado correctamente",
                    Toast.LENGTH_LONG
                ).show()
            },
            Response.ErrorListener { response -> // error
                Toast.makeText(
                    applicationContext,
                    "Se produjo un error al agregar el usuario",
                    Toast.LENGTH_LONG
                ).show()
            }
        ) {
            override fun getBodyContentType(): String {
                return "application/json"
            }

            override fun getBody(): ByteArray {
                return jsonBody.toString().toByteArray()
            }
        }
        queue.add(postRequest)
    }

    private fun eliminarUsuario() {
        // Obtener el ID del usuario a eliminar
        val id = txtId.text.toString()

        // Crear la cola de solicitudes de Volley
        val queue = Volley.newRequestQueue(this)
        val url = getString(R.string.urlAPI) + "/usuarios/$id"
        val deleteRequest: StringRequest = object : StringRequest(
            Request.Method.DELETE, url,
            Response.Listener { response -> // respuesta
                Toast.makeText(
                    applicationContext,
                    "Usuario eliminado correctamente",
                    Toast.LENGTH_LONG
                ).show()
            },
            Response.ErrorListener { response -> // error
                Toast.makeText(
                    applicationContext,
                    "Se produjo un error al eliminar el usuario",
                    Toast.LENGTH_LONG
                ).show()
            }
        ) {}
        queue.add(deleteRequest)
    }

    private fun editarUsuario() {
        // Obtener los valores de los campos de entrada
        val id = txtId.text.toString()
        val nombre = txtUsuarios.text.toString()
        val claves = txtClaves.text.toString()
        val estados = cmbEstados.selectedItem.toString()

        // Crear la cola de solicitudes de Volley
        val queue = Volley.newRequestQueue(this)
        val url = getString(R.string.urlAPI) + "/usuarios/$id"
        val putRequest: JsonObjectRequest = object : JsonObjectRequest(
            Method.PUT, url, null,
            Response.Listener { response ->
                Toast.makeText(
                    applicationContext,
                    "Usuario actualizado correctamente",
                    Toast.LENGTH_LONG
                ).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(
                    applicationContext,
                    "Se produjo un error al actualizar el usuario",
                    Toast.LENGTH_LONG
                ).show()
            }
        ) {
            override fun getBodyContentType(): String {
                return "application/json"
            }

            override fun getBody(): ByteArray {
                val params = JSONObject()
                params.put("id", id)
                params.put("usuario", nombre)
                params.put("clave", claves)
                params.put("estado", estados)
                return params.toString().toByteArray()
            }
        }
        queue.add(putRequest)
    }


    private fun getEstadoIndex(estado: String): Int {
        return when (estado) {
            "A" -> 0
            "X" -> 1
            else -> 0
        }
    }
}
