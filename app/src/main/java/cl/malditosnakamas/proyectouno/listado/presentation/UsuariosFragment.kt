package cl.malditosnakamas.proyectouno.listado.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import cl.malditosnakamas.proyectouno.R
import cl.malditosnakamas.proyectouno.databinding.FragmentUsuariosBinding
import cl.malditosnakamas.proyectouno.listado.data.local.UsuariosMapper
import cl.malditosnakamas.proyectouno.listado.domain.ObtenerUsuariosUseCase
import cl.malditosnakamas.proyectouno.listado.domain.UsuariosRepository
import cl.malditosnakamas.proyectouno.listado.domain.model.Usuario

class UsuariosFragment: Fragment(R.layout.fragment_usuarios),
    ItemClickListener {

    private lateinit var binding: FragmentUsuariosBinding
    private lateinit var usuariosAdapter: UsuariosAdapter
    private lateinit var useCase: ObtenerUsuariosUseCase
    private lateinit var repository: UsuariosRepository
    private val mapper = UsuariosMapper()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupUseCase()
        binding = FragmentUsuariosBinding.bind(view)

    }

    private fun setupUseCase() {
        // TODO: AIURAAAAA!!!
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvUsuarios.setHasFixedSize(true)
            rvUsuarios.layoutManager = GridLayoutManager(
                requireContext(),
                2,
                GridLayoutManager.VERTICAL,
                false)
        }
    }

    override fun onItemClick(usuario: Usuario) {
        // TODO: AIURAAAAA!!!
    }

}