package com.miempresa.usofragmentosv4.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.miempresa.usofragmentosv4.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Operaciones.newInstance] factory method to
 * create an instance of this fragment.
 */
class Operaciones : Fragment() {
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

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_operaciones, container, false)

        val txtNumero1 = view.findViewById<EditText>(R.id.txtNumero1)
        val txtNumero2 = view.findViewById<EditText>(R.id.txtNumero2)
        val spinnerAccion = view.findViewById<Spinner>(R.id.spinnerAccion)
        val btnEnviar = view.findViewById<Button>(R.id.btnEnviar)

        btnEnviar.setOnClickListener {
            val numero1 = txtNumero1.text.toString().toDoubleOrNull()
            val numero2 = txtNumero2.text.toString().toDoubleOrNull()
            val accion = spinnerAccion.selectedItem.toString()

            if (numero1 == null || numero2 == null) {
                Toast.makeText(requireContext(), "Campos vacíos detectados", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultado: Double = when (accion) {
                "Suma" -> numero1 + numero2
                "Resta" -> numero1 - numero2
                "Multiplicación" -> numero1 * numero2
                "División" -> {
                    if (numero2 != 0.0) {
                        numero1 / numero2
                    } else {
                        Toast.makeText(requireContext(), "No se puede dividir entre cero",
                            Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }
                else -> 0.0
            }

            val bundle = Bundle()
            bundle.putString("numerosIngresados", "Número 1: $numero1 \nNúmero 2: $numero2")
            bundle.putString("operacionElegida", accion)
            bundle.putString("resultado", resultado.toString())

            val respuestaFragment = RespuestaFragmento()
            respuestaFragment.arguments = bundle

            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.ContenedorAdd, respuestaFragment)
            fragmentTransaction.commit()
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Operaciones.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Operaciones().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
