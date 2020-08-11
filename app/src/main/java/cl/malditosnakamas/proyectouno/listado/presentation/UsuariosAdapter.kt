package cl.malditosnakamas.proyectouno.listado.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.malditosnakamas.proyectouno.listado.domain.model.Persona

class UsuariosAdapter(private val personas: List<Persona>,
                      private val listener: ItemClickListener
): RecyclerView.Adapter<UsuariosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): UsuariosViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return personas.size
    }

    override fun onBindViewHolder(holder: UsuariosViewHolder,
                                  position: Int) {
        TODO("Not yet implemented")
    }
}