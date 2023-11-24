package com.miempresa.serviciowebv4

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorUsuarios(val listaUsuarios: ArrayList<Usuario>): RecyclerView.Adapter<AdaptadorUsuarios.ViewHolder>() {

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val fNombre = itemView.findViewById<TextView>(R.id.elemento_usuario)
        val fEstados = itemView.findViewById<TextView>(R.id.elemento_estado)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = listaUsuarios[position]

        holder.fNombre.text = usuario.nombre
        holder.fEstados.text = "Estado : " + usuario.estados

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, verUsuario::class.java)
            intent.putExtra("id", usuario.id.toString())
            intent.putExtra("nombre", usuario.nombre)
            intent.putExtra("estados", usuario.estados)
            intent.putExtra("clave", usuario.clave)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.elementosusuarios, parent, false)
        return ViewHolder(view)
    }

    fun actualizarListaUsuarios(nuevaLista: ArrayList<Usuario>) {
        listaUsuarios.clear()
        listaUsuarios.addAll(nuevaLista)
        notifyDataSetChanged()
    }
}
