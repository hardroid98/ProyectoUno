package cl.malditosnakamas.proyectouno.listado.domain

import cl.malditosnakamas.proyectouno.listado.domain.model.Personas
import io.reactivex.Single

interface PersonasRepository {
    fun getAll(): Single<Personas>
}