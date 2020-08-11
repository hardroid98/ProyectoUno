package cl.malditosnakamas.proyectouno.login.domain

import io.reactivex.Single

interface LoginRepository {
    fun login(login: Login): Single<Boolean>
}