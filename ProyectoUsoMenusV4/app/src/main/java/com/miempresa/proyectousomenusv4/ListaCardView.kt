package com.miempresa.proyectousomenusv4

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import layout.AdaptadorCardView

class ListaCardView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_card_view)

        setSupportActionBar(findViewById(R.id.mitoolbar))
        //supportActionBar?.setHomeAsUpIndicator(android.R.drawable.ic_menu_preferences)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val lista = findViewById<RecyclerView>(R.id.lista)
        lista.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lista.layoutManager = LinearLayoutManager(this);

        var llenarLista = ArrayList<Elementos>()
        for (i in 1 until 20) {
            llenarLista.add(Elementos("Elemento "+i,
                BitmapFactory.decodeResource(resources, R.drawable.perfil_avatar_hombre_icono_redondo_24640_14044)))
        }

        val adapter = AdaptadorCardView(llenarLista)
        lista.adapter = adapter
    }
}
