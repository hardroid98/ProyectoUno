package cl.malditosnakamas.proyectouno.listado.domain

class ObtenerPersonasUseCase (
    private var repository: PersonasRepository
) {
    fun excecute() = repository.getAll()
}