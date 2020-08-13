package cl.malditosnakamas.proyectouno.listado.presentation

import cl.malditosnakamas.proyectouno.listado.domain.model.Usuarios

sealed class ListadoState(
    open val result: Usuarios? = null,
    open val error: Throwable? = null
) {
    object LoadingState: ListadoState()
    data class Complete(override val result: Usuarios?): ListadoState(result = result)
    data class Error(override val error: Throwable?): ListadoState(error = error)
}
