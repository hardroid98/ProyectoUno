package cl.malditosnakamas.proyectouno.listado.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cl.malditosnakamas.proyectouno.databinding.ItemUsuariosBinding
import cl.malditosnakamas.proyectouno.listado.domain.model.Persona

class UsuariosViewHolder(itemView: View, private val listener: ItemClickListener): RecyclerView.ViewHolder(itemView) {

    private lateinit var binding : ItemUsuariosBinding

    fun bind(persona: Persona) {
        binding.apply {
            tvNombre.text = persona.nombre
            tvRut.text = persona.rut
            tvMail.text = persona.email
        }
    }

    fun goToDetalle(persona: Persona) {
        listener.onItemClick(persona)
    }
}
