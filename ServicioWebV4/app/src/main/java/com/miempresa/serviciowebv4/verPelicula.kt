package com.miempresa.serviciowebv4

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_ver_pelicula.*
import java.util.HashMap

class verPelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_pelicula)

        val categorias = arrayOf("Drama" , "Comedia", "Anime" , "Terror" , "Accion")
        cmbCategoria.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, categorias
            )
        )

        btnGuardar.setOnClickListener {
            agregarPelicula()
        }

        btnEliminar.setOnClickListener {
            eliminarPelicula()
        }

        btnEditar.setOnClickListener {
            editarPelicula()
        }

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            txtId.setText(bundle.getString("id").toString())
            txtUsuarios.setText(bundle.getString("nombre").toString())
            when (bundle.getString("categoria").toString()) {
                "Drama" -> cmbCategoria.setSelection(0)
                "Comedia" -> cmbCategoria.setSelection(1)
                "Anime" -> cmbCategoria.setSelection(2)
                "Terror" -> cmbCategoria.setSelection(3)
                "Accion" -> cmbCategoria.setSelection(4)
            }
            txtClaves.setText(bundle.getString("duracion").toString())
            txtEstados.setText(bundle.getString("imagen").toString())

            btnGuardar.isEnabled = false
            btnEliminar.isEnabled = true
            btnEditar.isEnabled = true
        } else {
            btnGuardar.isEnabled = true
            btnEliminar.isEnabled = false
            btnEditar.isEnabled = false
        }
    }

    private fun agregarPelicula() {
        AsyncTask.execute {
            val id = txtId.text.toString()
            val nombre = txtUsuarios.text.toString()
            val categoria = cmbCategoria.selectedItem.toString()
            val duracion = txtClaves.text.toString()
            val imagen = txtEstados.text.toString()

            val queue = Volley.newRequestQueue(this)
            val url = getString(R.string.urlAPI) + "/peliculas"
            val postRequest: StringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener { response -> // response
                    Toast.makeText(
                        applicationContext,
                        "Registro agregado correctamente",
                        Toast.LENGTH_LONG
                    ).show()
                },
                Response.ErrorListener { response ->// error
                    Toast.makeText(
                        applicationContext,
                        "Se produjo un error al guardar los datos",
                        Toast.LENGTH_LONG
                    ).show()
                }
            ) {
                override fun getParams(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["id"] = id
                    params["nombre"] = nombre
                    params["categoria"] = categoria
                    params["duracion"] = duracion
                    params["imagen"] = imagen
                    return params
                }
            }
            queue.add(postRequest)
        }
    }

    private fun eliminarPelicula() {
        AsyncTask.execute {
            val id = txtId.text.toString()

            val queue = Volley.newRequestQueue(this)
            val url = getString(R.string.urlAPI) + "/peliculas/" + id
            val deleteRequest: StringRequest = object : StringRequest(
                Request.Method.DELETE, url,
                Response.Listener { response -> // response
                    Toast.makeText(
                        applicationContext,
                        "Registro eliminado correctamente",
                        Toast.LENGTH_LONG
                    ).show()
                },
                Response.ErrorListener { response ->// error
                    Toast.makeText(
                        applicationContext,
                        "Se produjo un error al eliminar el registro",
                        Toast.LENGTH_LONG
                    ).show()
                }
            ) {}
            queue.add(deleteRequest)
        }
    }

    private fun editarPelicula() {
        val id = txtId.text.toString()
        val nombre = txtUsuarios.text.toString()
        val categoria = cmbCategoria.selectedItem.toString()
        val duracion = txtClaves.text.toString()
        val imagen = txtEstados.text.toString()

        val queue = Volley.newRequestQueue(this)
        val url = getString(R.string.urlAPI) + "/peliculas/" + id
        val putRequest: StringRequest = object : StringRequest(
            Request.Method.PUT, url,
            Response.Listener { response -> // response
                Toast.makeText(
                    applicationContext,
                    "Registro actualizado correctamente",
                    Toast.LENGTH_LONG
                ).show()
            },
            Response.ErrorListener { response ->// error
                Toast.makeText(
                    applicationContext,
                    "Se produjo un error al actualizar el registro",
                    Toast.LENGTH_LONG
                ).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["nombre"] = nombre
                params["categoria"] = categoria
                params["duracion"] = duracion
                params["imagen"] = imagen
                return params
            }
        }
        queue.add(putRequest)
    }
}
