package cl.malditosnakamas.proyectouno.login.domain

import cl.malditosnakamas.proyectouno.registro.domain.Registro
import cl.malditosnakamas.proyectouno.registro.domain.RegistroRepository

class LoginUseCase (
    private val repository: LoginRepository){

        fun execute(login: Login) = repository.login(login)
    }
