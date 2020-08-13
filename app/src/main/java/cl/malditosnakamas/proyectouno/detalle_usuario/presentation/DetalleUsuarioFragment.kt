package cl.malditosnakamas.proyectouno.detalle_usuario.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import cl.malditosnakamas.proyectouno.R
import cl.malditosnakamas.proyectouno.databinding.FragmentDetalleUsuarioBinding
import cl.malditosnakamas.proyectouno.detalle_usuario.domain.DetalleRepository
import cl.malditosnakamas.proyectouno.detalle_usuario.domain.DetalleUseCase


class DetalleUsuarioFragment : Fragment(R.layout.fragment_detalle_usuario){

    lateinit var binding: FragmentDetalleUsuarioBinding
    lateinit var detalleUseCase : DetalleUseCase
    lateinit var repository: DetalleRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependecias()
        binding = FragmentDetalleUsuarioBinding.bind(view)
    }

    private fun setupDependecias() {
    }

}
