package com.miempresa.serviciowebv4

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class FragmentPeliculas : Fragment() {

    private lateinit var btnAgregar: Button
    private lateinit var btnBuscar: Button
    private lateinit var txtBuscar: EditText
    private lateinit var lista: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_peliculas, container, false)

        btnAgregar = view.findViewById(R.id.btnAgregar)
        btnBuscar = view.findViewById(R.id.btnBuscar)
        txtBuscar = view.findViewById(R.id.txtBuscarUsuarios)
        lista = view.findViewById(R.id.listaUsuarios)

        btnAgregar.setOnClickListener {
            val intent = Intent(activity, verPelicula::class.java)
            startActivity(intent)
        }

        btnBuscar.setOnClickListener {
            cargarLista()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lista.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        lista.layoutManager = LinearLayoutManager(activity)

        btnBuscar.setOnClickListener {
            cargarLista()
        }

        cargarLista()
    }

    private fun cargarLista() {
        lista.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        lista.layoutManager = LinearLayoutManager(activity)
        val llenarLista = ArrayList<Elementos>()
        val searchTerm = txtBuscar.text.toString().trim()

        AsyncTask.execute {
            val queue = Volley.newRequestQueue(activity)
            val url = getString(R.string.urlAPI) + "/peliculas"
            val stringRequest = JsonArrayRequest(url,
                { response ->
                    try {
                        for (i in 0 until response.length()) {
                            val id = response.getJSONObject(i).getString("id")
                            val nombre = response.getJSONObject(i).getString("nombre")
                            val duracion = response.getJSONObject(i).getString("duracion")
                            val imagen = response.getJSONObject(i).getString("imagen")
                            val categoria = response.getJSONObject(i).getString("categoria")

                            // Filtra los elementos por nombre o género que coincida con el término de búsqueda
                            if (nombre.startsWith(searchTerm, ignoreCase = true) || categoria.equals(
                                    searchTerm,
                                    ignoreCase = true
                                )
                            ) {
                                // Agrega el elemento a la lista
                                llenarLista.add(Elementos(id.toInt(), imagen, nombre, duracion.toInt(), categoria))
                            }
                        }

                        val adapter = AdaptadorElementos(llenarLista)
                        lista.adapter = adapter
                    } catch (e: JSONException) {
                        Toast.makeText(
                            activity,
                            "Error al obtener los datos",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }, {
                    Toast.makeText(
                        activity,
                        "Verifique que está conectado a internet",
                        Toast.LENGTH_LONG
                    ).show()
                })
            queue.add(stringRequest)
        }
    }
}
