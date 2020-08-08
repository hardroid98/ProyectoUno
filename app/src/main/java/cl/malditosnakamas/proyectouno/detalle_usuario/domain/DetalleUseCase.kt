package cl.malditosnakamas.proyectouno.detalle_usuario.domain

class DetalleUseCase (

    private val repository : DetalleRepository
){
    fun execute (detalle: Detalle) = repository.detalle(detalle)
}
