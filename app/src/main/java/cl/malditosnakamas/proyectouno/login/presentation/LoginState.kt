package cl.malditosnakamas.proyectouno.login.presentation

sealed class LoginState(
    open val result: Boolean? = null,
    open val error: Throwable? = null
) {
    object LoadingState : LoginState()
    data class Complete(override val result: Boolean?) : LoginState(result = result)
    data class Error(override val error: Throwable?) : LoginState(error = error)
}
