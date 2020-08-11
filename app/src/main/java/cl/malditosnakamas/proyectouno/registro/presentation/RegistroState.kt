package cl.malditosnakamas.proyectouno.registro.presentation

sealed class RegistroState(
    open val result: Boolean? = null,
    open val error: Throwable? = null
){
    object Loading : RegistroState()
    data class Complete(override val result: Boolean?) : RegistroState()
    data class Error(override val error: Throwable?) : RegistroState()
}