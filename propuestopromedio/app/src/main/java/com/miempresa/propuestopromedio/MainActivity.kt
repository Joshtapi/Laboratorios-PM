package com.miempresa.propuestopromedio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    fun calcularPract(num1:Double,num2:Double,num3:Double,num4:Double):Double{
        return (num1 + num2 + num3 + num4) / 4
    }

    fun calcularLabs(n1:Double,n2:Double,n3:Double,n4:Double,n5:Double,n6:Double):Double{
        return (n1 + n2 + n3 + n4 + n5 + n6 )/6
    }

    fun PromTotal(numero1:Double,numero2:Double):Double{
        return (numero1 * 0.4 + numero2 * 0.6)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var  btnCalcular = findViewById<Button>(R.id.btnCalcular)
        var  txtPract1 = findViewById<EditText>(R.id.txtPract1)
        var  txtPract2 = findViewById<EditText>(R.id.txtPract2)
        var  txtPract3 = findViewById<EditText>(R.id.txtPract3)
        var  txtPract4 = findViewById<EditText>(R.id.txtPract4)
        var  txtRespuesta = findViewById<EditText>(R.id.txtRespuesta)
        var  btnCalcular2 = findViewById<Button>(R.id.btnCalcular2)
        var  txtLab1 = findViewById<EditText>(R.id.txtLab1)
        var  txtLab2 = findViewById<EditText>(R.id.txtLab2)
        var  txtLab3 = findViewById<EditText>(R.id.txtLab3)
        var  txtLab4 = findViewById<EditText>(R.id.txtLab4)
        var  txtLab5 = findViewById<EditText>(R.id.txtLab5)
        var  txtLab6 = findViewById<EditText>(R.id.txtLab6)
        var  txtRespuesta2 = findViewById<EditText>(R.id.txtRespuesta2)
        var  btnPromFinal = findViewById<Button>(R.id.btnPromFinal)
        var  txtProm1 = findViewById<EditText>(R.id.txtProm1)
        var  txtProm2 = findViewById<EditText>(R.id.txtProm2)
        var  txtFinal = findViewById<EditText>(R.id.txtFinal)


        btnCalcular.setOnClickListener() {
            var numero1 = txtPract1.text.toString().toDouble()
            var numero2 = txtPract2.text.toString().toDouble()
            var numero3 = txtPract3.text.toString().toDouble()
            var numero4 = txtPract4.text.toString().toDouble()
            var respuesta = (calcularPract(numero1,numero2,numero3,numero4)).toString()
            txtRespuesta.setText(respuesta)
        }

        btnCalcular2.setOnClickListener() {
            var num1 = txtLab1.text.toString().toDouble()
            var num2 = txtLab2.text.toString().toDouble()
            var num3 = txtLab3.text.toString().toDouble()
            var num4 = txtLab4.text.toString().toDouble()
            var num5 = txtLab5.text.toString().toDouble()
            var num6 = txtLab6.text.toString().toDouble()
            var respuesta = (calcularLabs(num1,num2,num3,num4,num5,num6)).toString()
            txtRespuesta2.setText(respuesta)
        }

        btnPromFinal.setOnClickListener() {
            var num1 = txtProm1.text.toString().toDouble()
            var num2 = txtProm2.text.toString().toDouble()
            var respuesta = (PromTotal(num1,num2)).toString()
            txtFinal.setText(respuesta)
        }

    }
}