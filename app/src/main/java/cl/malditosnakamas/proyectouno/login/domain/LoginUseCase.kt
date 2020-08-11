package cl.malditosnakamas.proyectouno.login.domain

class LoginUseCase(
    private val repository: LoginRepository
) {
    fun execute(login: Login) = repository.login(login)
}
