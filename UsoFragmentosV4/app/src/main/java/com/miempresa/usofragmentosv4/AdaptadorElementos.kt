import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miempresa.usofragmentosv4.DetallesContacto
import com.miempresa.usofragmentosv4.Elementos
import com.miempresa.usofragmentosv4.R

class AdaptadorElementos(private val listaElementos: ArrayList<Elementos>) : RecyclerView.Adapter<AdaptadorElementos.ViewHolder>() {

    override fun getItemCount(): Int {
        return listaElementos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val elemento = listaElementos[position]
        holder.bind(elemento)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.elementoslista, parent, false)
        return ViewHolder(itemView)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val fTexto: TextView = itemView.findViewById(R.id.elemento_texto)
        private val fImagen: ImageView = itemView.findViewById(R.id.elemento_imagen)
        private lateinit var elemento: Elementos

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(elemento: Elementos) {
            this.elemento = elemento
            fTexto.text = elemento.texto
            fImagen.setImageBitmap(elemento.imagen)
        }

        override fun onClick(view: View) {
            val context = view.context
            val intent = Intent(context, DetallesContacto::class.java)
            intent.putExtra("nombre", elemento.texto)
            context.startActivity(intent)
        }
    }
}
