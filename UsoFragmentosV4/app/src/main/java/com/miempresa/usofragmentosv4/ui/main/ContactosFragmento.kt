package com.miempresa.usofragmentosv4.ui.main

import AdaptadorElementos
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miempresa.usofragmentosv4.Elementos
import com.miempresa.usofragmentosv4.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContactosFragmento.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactosFragmento : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View = inflater.inflate(R.layout.fragment_contactos_fragmento,container,false)
        var lista:RecyclerView = view.findViewById(R.id.lista)
        lista.addItemDecoration(DividerItemDecoration(view.context,DividerItemDecoration.VERTICAL))
        lista.layoutManager = LinearLayoutManager(view.context);

        var llenarlista = ArrayList<Elementos>()
        for (i in 1 until  20){
            llenarlista.add(Elementos("Elemento "+i,
                 BitmapFactory.decodeResource(resources, android.R.drawable.ic_menu_zoom)))
        }

        val adapter = AdaptadorElementos(llenarlista)
        lista.adapter = adapter

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ContactosFragmento.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactosFragmento().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}