package com.miempresa.usosugarormv4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lista.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        lista.layoutManager = LinearLayoutManager(this);

        var usuariorepo = UsuarioRepositorio()
        var ListaUsuarios= usuariorepo.listar()

        val adapter = AdaptadorUsuarios(ListaUsuarios as ArrayList<Usuario>)
        lista.adapter = adapter

        btnAgregar.setOnClickListener(){
            var crearUsuario = Intent(this,RegistroUsuarios::class.java)
            startActivity(crearUsuario)
        }
    }

    override fun onRestart() {
        super.onRestart()
        var usuariorepo = UsuarioRepositorio()
        var ListaUsuarios = usuariorepo.listar()

        val adapter = AdaptadorUsuarios(ListaUsuarios as ArrayList<Usuario>)
        lista.adapter = adapter
    }
}