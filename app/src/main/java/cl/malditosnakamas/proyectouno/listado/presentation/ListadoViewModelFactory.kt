package cl.malditosnakamas.proyectouno.listado.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.malditosnakamas.proyectouno.listado.domain.ObtenerUsuariosUseCase

class ListadoViewModelFactory(
    private val obtenerUsuariosUseCase: ObtenerUsuariosUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(ObtenerUsuariosUseCase::class.java)
            .newInstance(obtenerUsuariosUseCase)
    }
}