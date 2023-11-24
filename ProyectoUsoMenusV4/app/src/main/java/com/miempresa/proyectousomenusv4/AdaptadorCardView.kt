package layout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.miempresa.proyectousomenusv4.Elementos
import com.miempresa.proyectousomenusv4.R

class AdaptadorCardView(val ListaElementos:ArrayList<Elementos>): RecyclerView.Adapter<AdaptadorCardView.ViewHolder>() {

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val fTexto = itemView.findViewById<TextView>(R.id.elemento_texto)
        val fImagen = itemView.findViewById<ImageView>(R.id.elemento_imagen);
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdaptadorCardView.ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.elementoscardview, parent, false);
        return AdaptadorCardView.ViewHolder(v);
    }

    override fun onBindViewHolder(holder: AdaptadorCardView.ViewHolder, position: Int) {
        holder?.fTexto?.text=ListaElementos[position].texto
        holder?.fImagen?.setImageBitmap(ListaElementos[position].imagen)

        holder.itemView.setOnClickListener(){
            Toast.makeText(holder.itemView.context ,
            "Pulsaste cardview "+(position+1), Toast.LENGTH_LONG).show()
        }
        var imgButton = holder.itemView.findViewById<ImageButton>(R.id.menu_button)
        imgButton.setOnClickListener(){
            Toast.makeText(holder.itemView.context ,
            "Pulsaste boton dentro de cardView "+(position+1), Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return ListaElementos.size;
    }
}
