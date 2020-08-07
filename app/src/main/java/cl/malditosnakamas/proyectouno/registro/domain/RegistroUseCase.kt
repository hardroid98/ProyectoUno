package cl.malditosnakamas.proyectouno.registro.domain

class RegistroUseCase(
    private val repository: RegistroRepository
) {
    fun execute(registro: Registro) = repository.registro(registro)
}