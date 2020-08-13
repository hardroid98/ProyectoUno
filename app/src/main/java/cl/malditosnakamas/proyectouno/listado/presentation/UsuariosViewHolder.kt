package cl.malditosnakamas.proyectouno.listado.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cl.malditosnakamas.proyectouno.databinding.ItemUsuariosBinding
import cl.malditosnakamas.proyectouno.listado.domain.model.Usuario

class UsuariosViewHolder(itemView: View, private val listener: ItemClickListener): RecyclerView.ViewHolder(itemView) {

    private val binding = ItemUsuariosBinding.bind(itemView)

    fun bind(usuario: Usuario) {
        binding.apply {
            tvNombre.text = usuario.nombre
            tvRut.text = usuario.rut
            tvMail.text = usuario.email
        }
    }

    fun goToDetalle(usuario: Usuario) {
        listener.onItemClick(usuario)
    }
}
