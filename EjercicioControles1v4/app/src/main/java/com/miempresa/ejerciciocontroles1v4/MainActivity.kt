package com.miempresa.ejerciciocontroles1v4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var  btnEvaluar = findViewById<Button>(R.id.btnEvaluar)
        var  txtNumero = findViewById<EditText>(R.id.txtNumero)
        var  radioPar = findViewById<RadioButton>(R.id.radioPar)
        var  radioImpar = findViewById<RadioButton>(R.id.radioImpar)

        btnEvaluar.setOnClickListener(){
            var numero:Int = txtNumero.text.toString().toInt()
            if (numero % 2 == 0 ){
                radioPar.isChecked = true

            }else{
                radioImpar.isChecked = true
            }
        }
    }
}