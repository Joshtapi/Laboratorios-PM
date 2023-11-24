package com.miempresa.usofragmentosv4.ui.main

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.miempresa.usofragmentosv4.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RespuestaFragmento.newInstance] factory method to
 * create an instance of this fragment.
 */
class RespuestaFragmento : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_respuesta, container, false)

        val lblNumerosIngresados = view.findViewById<TextView>(R.id.lblNumerosIngresados)
        val lblOperacionElegida = view.findViewById<TextView>(R.id.lblOperacionElegida)
        val lblResultado = view.findViewById<TextView>(R.id.lblResultado)

        val numerosIngresados = arguments?.getString("numerosIngresados") ?: ""
        val operacionElegida = arguments?.getString("operacionElegida") ?: ""
        val resultadoString = arguments?.getString("resultado") ?: ""

        val resultado = if (resultadoString.isNullOrEmpty()) {
            ""
        } else {
            try {
                resultadoString.toDouble().let { String.format("%.2f", it) }
            } catch (e: NumberFormatException) {
                null
            }
        }

        val numerosIngresadosText = "Números ingresados \n  \n $numerosIngresados"
        val operacionElegidaText = "Operación elegida \n  \n$operacionElegida"
        val resultadoText = "Resultado \n  \n$resultado"

        val colorTeal = ContextCompat.getColor(requireContext(), R.color.a)

        val numerosIngresadosSpannable = SpannableString(numerosIngresadosText)
        val operacionElegidaSpannable = SpannableString(operacionElegidaText)
        val resultadoSpannable = SpannableString(resultadoText)

        val numerosIngresadosIndex = numerosIngresadosText.indexOf(numerosIngresados)
        val operacionElegidaIndex = operacionElegidaText.indexOf(operacionElegida)
        val resultadoIndex = resultado?.let { resultadoText.indexOf(it) }

        numerosIngresadosSpannable.setSpan(
            ForegroundColorSpan(colorTeal), numerosIngresadosIndex,
            numerosIngresadosIndex + numerosIngresados.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        operacionElegidaSpannable.setSpan(
            ForegroundColorSpan(colorTeal), operacionElegidaIndex,
            operacionElegidaIndex + operacionElegida.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        if (resultadoIndex != null) {
            resultadoSpannable.setSpan(
                ForegroundColorSpan(colorTeal), resultadoIndex,
                resultadoIndex + resultado.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        lblNumerosIngresados.text = numerosIngresadosSpannable
        lblOperacionElegida.text = operacionElegidaSpannable
        lblResultado.text = resultadoSpannable

        return view
    }
}