package cl.malditosnakamas.proyectouno.registro.domain

import io.reactivex.Single

interface RegistroRepository {
    fun registro(registro: Registro) : Single<Boolean>
}