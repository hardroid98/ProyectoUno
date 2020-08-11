package cl.malditosnakamas.proyectouno.listado.data.local

import cl.malditosnakamas.proyectouno.listado.data.local.database.UsuariosEntity
import cl.malditosnakamas.proyectouno.listado.domain.model.Usuario

class UsuariosMapper {
    fun mapDomainToRoom(usuario: Usuario): UsuariosEntity {
        return UsuariosEntity(
            nombre = usuario.nombre,
            rut = usuario.rut,
            clave = usuario.clave,
            email = usuario.email
        )
    }
}