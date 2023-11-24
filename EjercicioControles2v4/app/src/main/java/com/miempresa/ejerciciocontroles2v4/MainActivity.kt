package com.miempresa.ejerciciocontroles2v4

import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var valor1EditText: EditText
    private lateinit var valor2EditText: EditText
    private lateinit var resultadoEditText: EditText
    private lateinit var operacionRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        valor1EditText = findViewById(R.id.txtValor1)
        valor2EditText = findViewById(R.id.txtValor2)
        resultadoEditText = findViewById(R.id.txtResultado)
        operacionRadioGroup = findViewById(R.id.radioGroup)

        operacionRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val operacion = findViewById<RadioButton>(checkedId).text.toString()
            calcular(operacion)
        }
    }

    private fun calcular(operacion: String) {
        val valor1 = valor1EditText.text.toString().toDouble()
        val valor2 = valor2EditText.text.toString().toDouble()
        var resultado = 0.0

        when (operacion) {
            "+" -> resultado = valor1 + valor2
            "-" -> resultado = valor1 - valor2
            "x" -> resultado = valor1 * valor2
            "/" -> resultado = valor1 / valor2
        }

        resultadoEditText.setText(resultado.toString())
    }
}
