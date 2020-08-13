package cl.malditosnakamas.proyectouno.listado.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.malditosnakamas.proyectouno.R
import cl.malditosnakamas.proyectouno.listado.domain.model.Usuario

class UsuariosAdapter(private val usuarios: List<Usuario>,
                      private val listener: ItemClickListener
): RecyclerView.Adapter<UsuariosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): UsuariosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_usuarios,
            parent, false)
        return UsuariosViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }

    override fun onBindViewHolder(holder: UsuariosViewHolder,
                                  position: Int) {
        holder.bind(usuarios[position])
    }
}