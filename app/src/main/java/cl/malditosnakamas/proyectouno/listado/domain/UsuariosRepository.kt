package cl.malditosnakamas.proyectouno.listado.domain

import cl.malditosnakamas.proyectouno.listado.domain.model.Usuarios
import io.reactivex.Single

interface UsuariosRepository {
    fun obtenerDatos(): Single<Usuarios>
}