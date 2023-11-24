package com.miempresa.googlemapv4
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AgregarDestinoActivity : AppCompatActivity() {

    private lateinit var etDestino: EditText
    private lateinit var etLatitud: EditText
    private lateinit var etLongitud: EditText
    private lateinit var etInfo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_destino)

        etDestino = findViewById(R.id.etDestino)
        etLatitud = findViewById(R.id.etLatitud)
        etLongitud = findViewById(R.id.etLongitud)
        etInfo = findViewById(R.id.etInfo)

        val btnGuardar: Button = findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener {
            guardarDestino()
        }
    }

    private fun guardarDestino() {
        val destino = etDestino.text.toString()
        val latitud = etLatitud.text.toString()
        val longitud = etLongitud.text.toString()
        val info = etInfo.text.toString()

        val mensaje = "Destino: $destino\nLatitud: $latitud\nLongitud: $longitud\nInformación: $info"
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

        finish()
    }
}