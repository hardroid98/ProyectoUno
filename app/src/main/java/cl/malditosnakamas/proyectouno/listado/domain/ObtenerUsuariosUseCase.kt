package cl.malditosnakamas.proyectouno.listado.domain

class ObtenerUsuariosUseCase (
    private var repository: UsuariosRepository
) {
    fun excecute() = repository.obtenerDatos()
}