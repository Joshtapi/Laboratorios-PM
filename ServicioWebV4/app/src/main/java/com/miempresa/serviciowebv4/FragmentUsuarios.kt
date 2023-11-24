package com.miempresa.serviciowebv4

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
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
import kotlinx.android.synthetic.main.fragment_usuarios.*
import org.json.JSONException

class FragmentUsuarios : Fragment() {

    private lateinit var btnAgregarUsuario: Button
    private lateinit var btnBuscarUsuario: Button
    private lateinit var txtBuscarUsuarios: EditText
    private lateinit var listaUsuarios: RecyclerView
    private lateinit var adapter: AdaptadorUsuarios

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_usuarios, container, false)

        btnAgregarUsuario = view.findViewById(R.id.btnAgregarUsuario)
        btnBuscarUsuario = view.findViewById(R.id.btnBuscarUsuario)
        txtBuscarUsuarios = view.findViewById(R.id.txtBuscarUsuarios)
        listaUsuarios = view.findViewById(R.id.listaUsuarios)

        btnAgregarUsuario.setOnClickListener {
            val intent = Intent(activity, verUsuario::class.java)
            startActivity(intent)
        }

        btnBuscarUsuario.setOnClickListener {
            cargarListas()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listaUsuarios.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        listaUsuarios.layoutManager = LinearLayoutManager(activity)
        adapter = AdaptadorUsuarios(ArrayList())
        listaUsuarios.adapter = adapter

        btnBuscarUsuario.setOnClickListener {
            cargarListas()
        }

        cargarListas()
    }


    private fun cargarListas() {
        val searchTerm = txtBuscarUsuarios.text.toString().trim()

        AsyncTask.execute {
            val queue = Volley.newRequestQueue(activity)
            val url = getString(R.string.urlAPI) + "/usuarios"
            val stringRequest = JsonArrayRequest(url,
                { response ->
                    try {
                        val llenarLista = ArrayList<Usuario>()

                        for (i in 0 until response.length()) {
                            val jsonObject = response.getJSONObject(i)
                            val id = jsonObject.getInt("id")
                            val nombre = jsonObject.getString("usuario")
                            val estado = jsonObject.getString("estado")
                            val clave = jsonObject.getString("clave")

                            // Filtra los elementos por nombre o estado que coincida con el término de búsqueda
                            if (nombre.startsWith(searchTerm, ignoreCase = true) || estado.equals(searchTerm, ignoreCase = true)) {
                                // Agrega el elemento a la lista
                                llenarLista.add(Usuario(id, nombre, estado,clave))
                            }
                        }

                        activity?.runOnUiThread {
                            // Actualiza la lista de usuarios en el adaptador
                            adapter.actualizarListaUsuarios(llenarLista)
                            // Notifica los cambios al adaptador y a la vista
                            adapter.notifyDataSetChanged()
                        }
                    } catch (e: JSONException) {
                        activity?.runOnUiThread {
                            Toast.makeText(
                                activity,
                                "Error al obtener los datos",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }, {
                    activity?.runOnUiThread {
                        Toast.makeText(
                            activity,
                            "Verifique que está conectado a internet",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
            queue.add(stringRequest)
        }
    }

}
