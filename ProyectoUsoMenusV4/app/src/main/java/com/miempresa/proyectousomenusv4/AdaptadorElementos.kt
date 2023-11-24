package com.miempresa.proyectousomenusv4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AdaptadorElementos(val ListaElementos:ArrayList<Elementos>):RecyclerView.Adapter<AdaptadorElementos.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val fTexto = itemView.findViewById<TextView>(R.id.elemento_texto)
        val fImagen = itemView.findViewById<ImageView>(R.id.elemento_imagen)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdaptadorElementos.ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.elementoslista,parent,false);
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder?.fTexto?.text=ListaElementos[position].texto
        holder?.fImagen?.setImageBitmap(ListaElementos[position].imagen)

        holder.itemView.setOnCreateContextMenuListener{contextMenu, _, _ ->
            contextMenu.setHeaderTitle("Se eligio opcion " + (position+1))
            true
        }
    }

    override fun getItemCount(): Int {
        return ListaElementos.size;
    }
}