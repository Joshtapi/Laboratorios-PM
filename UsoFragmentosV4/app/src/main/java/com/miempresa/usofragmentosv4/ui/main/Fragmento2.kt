package com.miempresa.usofragmentosv4.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.miempresa.usofragmentosv4.R

class Fragmento2 : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_fragmento2, container, false)
        val txtEnviar = view.findViewById<EditText>(R.id.txtEnviar)
        val btnEnviar = view.findViewById<Button>(R.id.btnEnviar)

        btnEnviar.setOnClickListener(View.OnClickListener {
            val fragmento1 = Fragmento1()
            val args = Bundle()
            args.putString("texto", txtEnviar.text.toString())
            fragmento1.arguments = args

            val transaccion: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaccion.replace(R.id.contenedor, fragmento1)
            transaccion.commit()
        })

        return view
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragmento2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
